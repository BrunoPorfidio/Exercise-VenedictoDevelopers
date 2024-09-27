package com.microservice.venedicto.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class UserDTO {

    private UUID id;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;
    private String name;
    private String email;
    private String password;
    private List<UserPhoneDTO> userPhone;

    public UserDTO(UUID id, LocalDateTime created, LocalDateTime lastLogin, String token, boolean isActive, String name, String email, String password, List<UserPhoneDTO> phones) {
        this.id = id;
        this.created = created;
        this.lastLogin = lastLogin;
        this.token = token;
        this.isActive = isActive;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userPhone = phones;
    }

    // Inner class for UserPhoneDTO
    @Data
    public static class UserPhoneDTO {

        private Long number;
        private int cityCode;
        private String contryCode;

        public UserPhoneDTO(Long number, int cityCode, String contryCode) {
            this.number = number;
            this.cityCode = cityCode;
            this.contryCode = contryCode;
        }

    }

}
