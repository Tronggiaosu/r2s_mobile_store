package com.example.r2s_mobile_store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.example.r2s_mobile_store.dto.ProductDTO;
import com.example.r2s_mobile_store.dto.ProductUpdateDTO;
import com.example.r2s_mobile_store.entity.Product;

@Mapper(componentModel = "spring", uses = { CategoryMapper.class, VariantProductMapper.class, ThumbnailMapper.class })
@Component
public interface ProductMapper {
    @Mapping(source = "category", target = "category.id")
    @Mapping(source = "variantProductsDTOs", target = "variantProducts")
    @Mapping(source = "thumbnailDTOs", target = "thumbnails")
    Product toEntity(ProductDTO productDTO);

    @Mapping(source = "category.id", target = "category")
    @Mapping(source = "variantProducts", target = "variantProductsDTOs")
    @Mapping(source = "thumbnails", target = "thumbnailDTOs")
    ProductDTO toDTO(Product product);

    @Mapping(source = "category", target = "category.id")
    @Mapping(source = "variantProductsDTOs", target = "variantProducts")
    @Mapping(source = "thumbnailDTOs", target = "thumbnails")
    Product toEntity(ProductUpdateDTO productUpdateDTO);

    @Mapping(source = "category.id", target = "category")
    @Mapping(source = "variantProducts", target = "variantProductsDTOs")
    @Mapping(source = "thumbnails", target = "thumbnailDTOs")
    ProductUpdateDTO toUpdateDTO(Product product);
}
