package com.example.r2s_mobile_store.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderDTO {
    private long id;
    private float totalPrice;
    private long totalQuantity;
    private long user;
    private List<OrderDetailDTO> orderDetailDTOs;
}
