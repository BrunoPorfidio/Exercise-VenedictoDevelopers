package com.microservice.venedicto.security.Exceptions;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int codigo;
    private String detail;
    
    public ErrorResponse(LocalDateTime timestamp, int codigo, String detail) {
        this.timestamp = timestamp;
        this.codigo = codigo;
        this.detail = detail;
    }
}
