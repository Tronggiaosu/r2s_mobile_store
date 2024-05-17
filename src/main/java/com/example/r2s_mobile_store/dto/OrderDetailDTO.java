package com.example.r2s_mobile_store.dto;

import lombok.Data;

@Data
public class OrderDetailDTO {
    private long id;
    private long quantity;
    private float total;
    private VariantProductDTO variantProductDTO;
}