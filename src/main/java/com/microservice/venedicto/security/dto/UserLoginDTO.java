
package com.microservice.venedicto.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginDTO {
    @Email
    private String email;
    
    private String userName;

    @NotNull
    private String password;
}
