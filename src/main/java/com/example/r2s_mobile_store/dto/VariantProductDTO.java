package com.example.r2s_mobile_store.dto;

import lombok.Data;

@Data
public class VariantProductDTO {
    private long id;
    private String material;
    private String color;
    private String size;
    private float price;
    private long quantity;
}
