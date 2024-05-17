package com.example.r2s_mobile_store.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.r2s_mobile_store.dto.VariantProductDTO;
import com.example.r2s_mobile_store.exception.VariantProductNotFoundException;
import com.example.r2s_mobile_store.mapper.VariantProductMapper;
import com.example.r2s_mobile_store.repository.VariantProductRepository;
import com.example.r2s_mobile_store.service.VariantProductService;

@Service
public class VariantProductServiceImpl implements VariantProductService {
    private final VariantProductRepository variantProductRepository;
    private final VariantProductMapper variantProductMapper;

    public VariantProductServiceImpl(VariantProductRepository variantProductRepository,
            VariantProductMapper variantProductMapper) {
        this.variantProductRepository = variantProductRepository;
        this.variantProductMapper = variantProductMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<VariantProductDTO> findAll() {
        return variantProductRepository.findAll().stream()
                .map(variantProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public VariantProductDTO findById(long id) throws VariantProductNotFoundException {
        return variantProductMapper.toDTO(variantProductRepository.findById(id)
                .orElseThrow(() -> new VariantProductNotFoundException("Biến thể này không tồn tại")));
    }
}
