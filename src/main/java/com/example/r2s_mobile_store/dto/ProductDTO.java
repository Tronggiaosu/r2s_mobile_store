package com.example.r2s_mobile_store.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductDTO {
    private long id;
    private String name;
    private float price;
    private String description;
    private long category;
    private List<VariantProductDTO> variantProductsDTOs;
    private List<ThumbnailDTO> thumbnailDTOs;
}
