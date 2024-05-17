package com.example.r2s_mobile_store.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String gender;
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;
    private String address;
    private Long role;
    private boolean status;
}
