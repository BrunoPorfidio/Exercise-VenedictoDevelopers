package com.microservice.venedicto.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class UserPhoneDTO {

    public UUID id;
    public Long number;
    public int cityCode;
    public String contryCode;
    
    public UserPhoneDTO(Long number, int cityCode, String countryCode){
        this.number = number;
        this.cityCode = cityCode;
        this.contryCode = countryCode;
    }
    
}

