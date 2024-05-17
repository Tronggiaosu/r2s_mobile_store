package com.example.r2s_mobile_store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.r2s_mobile_store.constant.ApiUrlConstant;
import com.example.r2s_mobile_store.service.ThumbnailService;

@RestController
@RequestMapping(ApiUrlConstant.THUMBNAIL)
public class ThumbnailController {
    private final ThumbnailService thumbnailService;

    public ThumbnailController(ThumbnailService thumbnailService) {
        this.thumbnailService = thumbnailService;
    }

    @GetMapping
    public ResponseEntity<?> getAllThumbnails() {
        return ResponseEntity.ok(thumbnailService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getThumbnailById(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(thumbnailService.findById(id));
    }
}
