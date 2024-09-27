package com.microservice.venedicto.security.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class UserResponseDTO {
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;

   
    public UserResponseDTO(UUID id,LocalDateTime created, LocalDateTime lastLogin, String token, boolean isActive ){
        this.id = id;
        this.created = created;
        this.lastLogin = lastLogin;
        this.token = token;
        this.isActive = isActive;
    }

}
