package com.example.r2s_mobile_store.service;

import java.util.List;

import com.example.r2s_mobile_store.dto.ProductDTO;
import com.example.r2s_mobile_store.dto.ProductUpdateDTO;
import com.example.r2s_mobile_store.exception.CategoryNotFoundException;
import com.example.r2s_mobile_store.exception.ProductNotFoundException;

public interface ProductService {
    List<ProductDTO> findAll();

    List<ProductDTO> findAllByCategoryId(Long id) throws CategoryNotFoundException;

    ProductDTO findById(long id) throws ProductNotFoundException;

    ProductDTO create(ProductDTO productDTO);

    ProductUpdateDTO update(long id, ProductUpdateDTO productUpdateDTO) throws ProductNotFoundException;

    String delete(long id) throws ProductNotFoundException;
}
