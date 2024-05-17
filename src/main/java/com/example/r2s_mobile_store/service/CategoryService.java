package com.example.r2s_mobile_store.service;

import java.util.List;

import com.example.r2s_mobile_store.dto.CategoryDTO;
import com.example.r2s_mobile_store.exception.CategoryNotFoundException;

public interface CategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO findById(long id) throws CategoryNotFoundException;

    CategoryDTO create(CategoryDTO categoryDTO);

    CategoryDTO update(long id, CategoryDTO categoryDTO) throws CategoryNotFoundException;

    void delete(long id) throws CategoryNotFoundException;
}
