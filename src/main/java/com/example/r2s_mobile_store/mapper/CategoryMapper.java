package com.example.r2s_mobile_store.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.r2s_mobile_store.dto.CategoryDTO;
import com.example.r2s_mobile_store.entity.Category;

@Mapper(componentModel = "spring")
@Component
public interface CategoryMapper {
    Category toEntity(CategoryDTO categoryDTO);

    CategoryDTO toDTO(Category category);
}
