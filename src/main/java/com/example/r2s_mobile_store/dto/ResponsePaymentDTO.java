package com.example.r2s_mobile_store.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResponsePaymentDTO implements Serializable {
    private String status;
    private String message;
    private String URL;
}
