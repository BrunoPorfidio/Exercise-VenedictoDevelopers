package com.microservice.venedicto.security.controller;

import com.microservice.venedicto.dto.UserDTO;
import com.microservice.venedicto.model.User;
import com.microservice.venedicto.repository.UserRepository;
import com.microservice.venedicto.security.dto.UserResponseDTO;
import com.microservice.venedicto.security.jwt.JwtUtil;
import com.microservice.venedicto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Data;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // Método para validar email
    private boolean isValidEmail(String email) {
        String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Método para validar contraseña
    private boolean isValidPassword(String password) {
    String PASSWORD_REGEX = "^(?=.*[A-Z])(?!.*[A-Z].*[A-Z])(?=(.*\\d.*\\d))(?!.*\\d.*\\d.*\\d)(?=.*[a-z]).{8,12}$";
    Pattern pattern = Pattern.compile(PASSWORD_REGEX);
    Matcher matcher = pattern.matcher(password);
    return matcher.matches();
}


    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        try {
            //Asignamos la fecha de creacion, y volvemos verdadero que el Usuario esta Activo.
            user.setCreated(LocalDateTime.now());
            user.setActive(true);

            // Validar email y si el Email no es valido retorna un BAD_REQUEST.
            if (!isValidEmail(user.getEmail())) {
                return createErrorResponse("Invalid email format", HttpStatus.BAD_REQUEST);
            }
            if (userRepository.existsByEmail(user.getEmail())) {
                return createErrorResponse("Email Already Exist", HttpStatus.BAD_REQUEST);
            }

            // Validar contraseña
            if (!isValidPassword(user.getPassword())) {
                return createErrorResponse("Password must meet the criteria", HttpStatus.BAD_REQUEST);
            }

            // Crea el Usuario
            User createdUser = userService.signUp(user);

            // Genera el Token
            String token = jwtUtil.generateToken(user);
            createdUser.setToken(token);

            // Asignar la última fecha de login
            createdUser.setLastLogin(LocalDateTime.now());
            userRepository.save(createdUser);

            // Crear la respuesta con los campos solicitados
            UserResponseDTO userResponseDTO = new UserResponseDTO(
                    createdUser.getId(),
                    createdUser.getCreated(),
                    createdUser.getLastLogin(),
                    createdUser.getToken(),
                    createdUser.isActive()
            );

            return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);

        } catch (Exception e) {
            return createErrorResponse("Bad Credentials", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> getUserData(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        try {
            // Obtener el usuario a partir del token
            User user = userService.getUserFromToken(token);

            // Actualizar lastLogin a la fecha de cuando se ejecuta /login.
            user.setLastLogin(LocalDateTime.now());
            // Generar un nuevo token
            String newToken = jwtUtil.generateToken(user);
            user.setToken(newToken);  // Guardar el nuevo token en el usuario
            userRepository.save(user);

            List<UserDTO.UserPhoneDTO> phones = user.getPhones().stream()
                    .map(phone -> new UserDTO.UserPhoneDTO(phone.getNumber(), phone.getCityCode(), phone.getContryCode()))
                    .collect(Collectors.toList());

            // Retornar la respuesta con los datos del usuario y el nuevo token
            UserDTO userDTO = new UserDTO(
                    user.getId(),
                    user.getCreated(),
                    user.getLastLogin(),
                    newToken, // Se muestra el nuevo Token.
                    user.isActive(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword(),
                    phones
            );
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (Exception e) {
            return createErrorResponse("Unauthorized Acces.", HttpStatus.UNAUTHORIZED);
        }
    }

    private ResponseEntity<?> createErrorResponse(String message, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), status.value(), message);
        return new ResponseEntity<>(errorResponse, status);
    }

    @Data
    public static class ErrorResponse {

        private LocalDateTime timestamp;
        private int codigo;
        private String detail;

        public ErrorResponse(LocalDateTime timestamp, int codigo, String detail) {
            this.timestamp = timestamp;
            this.codigo = codigo;
            this.detail = detail;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDetail() {
            return detail;
        }
    }

    // Endpoint de mas para ver todos los Usuarios guardados en la DB y ser mas facil ver sus datos.
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            // Recuperar todos los usuarios de la base de datos
            List<User> users = (List<User>) userRepository.findAll();

            List<UserDTO> userDTOs = users.stream()
                    .map(user -> {
                        List<UserDTO.UserPhoneDTO> phones = user.getPhones().stream()
                                .map(phone -> new UserDTO.UserPhoneDTO(phone.getNumber(), phone.getCityCode(), phone.getContryCode()))
                                .collect(Collectors.toList());

                        return new UserDTO(
                                user.getId(),
                                user.getCreated(),
                                user.getLastLogin(),
                                user.getToken(),
                                user.isActive(),
                                user.getName(),
                                user.getEmail(),
                                user.getPassword(),
                                phones
                        );
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok(userDTOs);
        } catch (Exception e) {
            return createErrorResponse("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
