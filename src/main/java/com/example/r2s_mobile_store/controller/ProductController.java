package com.example.r2s_mobile_store.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.r2s_mobile_store.constant.ApiUrlConstant;
import com.example.r2s_mobile_store.constant.PaginateConstant;
import com.example.r2s_mobile_store.dto.ProductDTO;
import com.example.r2s_mobile_store.dto.ProductUpdateDTO;
import com.example.r2s_mobile_store.exception.ProductNotFoundException;
import com.example.r2s_mobile_store.service.ProductService;

@RestController
@RequestMapping(ApiUrlConstant.PRODUCT)
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/paginate")
    public ResponseEntity<?> getAllProductPaginate(
            @RequestParam(defaultValue = PaginateConstant.DEFAULT_PAGE_NUMBER) int no,
            @RequestParam(defaultValue = PaginateConstant.DEFAULT_PAGE_LIMIT) int limit) {
        try {
            return ResponseEntity.ok(productService.findAllProductPaginate(no, limit));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getAllProductByCategoryId(@PathVariable(value = "categoryId") long categoryId) {
        return ResponseEntity.ok(productService.findAllByCategoryId(categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.ok(productService.create(productDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") long id,
            @RequestBody ProductUpdateDTO productUpdateDTO) {
        try {
            return ResponseEntity.ok(productService.update(id, productUpdateDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
        try {
            productService.delete(id);
            return ResponseEntity.ok("Xoá sản phẩm thành công");
        } catch (ProductNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
