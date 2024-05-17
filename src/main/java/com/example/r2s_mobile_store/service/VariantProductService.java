package com.example.r2s_mobile_store.service;

import java.util.List;

import com.example.r2s_mobile_store.dto.VariantProductDTO;
import com.example.r2s_mobile_store.exception.VariantProductNotFoundException;

public interface VariantProductService {
    List<VariantProductDTO> findAll();

    VariantProductDTO findById(long id) throws VariantProductNotFoundException;
}
