package com.example.r2s_mobile_store.dto;

import lombok.Data;

@Data
public class ResponseLoginDTO {
    private String username;
    private String token;
    private String role;
}
