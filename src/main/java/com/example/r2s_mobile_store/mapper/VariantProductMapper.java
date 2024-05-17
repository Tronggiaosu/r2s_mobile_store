package com.example.r2s_mobile_store.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.r2s_mobile_store.dto.VariantProductDTO;
import com.example.r2s_mobile_store.entity.VariantProduct;

@Mapper(componentModel = "spring")
@Component
public interface VariantProductMapper {
    VariantProduct toEntity(VariantProductDTO variantProductDTO);

    VariantProductDTO toDTO(VariantProduct variantProduct);
}
