package com.example.r2s_mobile_store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.r2s_mobile_store.constant.ApiUrlConstant;
import com.example.r2s_mobile_store.service.VariantProductService;

@RestController
@RequestMapping(ApiUrlConstant.VARIANT_PRODUCT)
public class VariantProductController {
    private final VariantProductService variantProductService;

    public VariantProductController(VariantProductService variantProductService) {
        this.variantProductService = variantProductService;
    }

    @GetMapping
    public ResponseEntity<?> getAllVarriantProducts() {
        return ResponseEntity.ok(variantProductService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVariantProductById(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(variantProductService.findById(id));
    }
}
