//package com.microservice.venedicto.controller;
//
//import com.microservice.venedicto.dto.Message;
//import com.microservice.venedicto.dto.UserDTO;
//import com.microservice.venedicto.model.User;
//import com.microservice.venedicto.service.UserService;
//import io.micrometer.common.util.StringUtils;
//import java.util.List;
//import java.util.regex.Pattern;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//    
//    @GetMapping("/all")
//    public ResponseEntity<List<User>> getAllUsers(){
//        List<User> user = userService.findAll();
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//    
//    @PostMapping("/signup")
//    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
//        if (StringUtils.isBlank(userDTO.getEmail())) {
//            return new ResponseEntity<>(new Message("Email Empty"), HttpStatus.BAD_REQUEST);
//        }
//
//        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
//        if (!Pattern.matches(emailRegex, userDTO.getEmail())) {
//            return new ResponseEntity<>(new Message("Invalid Email Format"), HttpStatus.BAD_REQUEST);
//        }
//
////        if (userDTO.getEmail().equals(userDTO.getEmail())) {
////            return new ResponseEntity<>(new Message("Email already exists"), HttpStatus.BAD_REQUEST);
////        }
//
//        if (StringUtils.isBlank(userDTO.getEmail())) {
//            return new ResponseEntity<>(new Message("Email Empty"), HttpStatus.BAD_REQUEST);
//        }
//
//        if (StringUtils.isBlank(userDTO.getPassword())) {
//            return new ResponseEntity<>(new Message("Password Empty"), HttpStatus.BAD_REQUEST);
//        }
//
//        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d.*\\d)[A-Za-z\\d]{8,12}$";
//        if (!Pattern.matches(passwordRegex, userDTO.getPassword())) {
//            return new ResponseEntity<>(new Message("Invalid Password Format"), HttpStatus.BAD_REQUEST);
//        }
//
//        User newUser = new User();
//        newUser.setName(userDTO.getName());
//        newUser.setEmail(userDTO.getEmail());
//        newUser.setPassword(userDTO.getPassword());
//
//        userService.signUp(newUser);
//
//        return new ResponseEntity<>(new Message("User Created"), HttpStatus.CREATED);
//    }
//}
