package com.microservice.venedicto.model;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserPhone {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private Long number;
    private int cityCode;
    private String contryCode;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
