package com.example.r2s_mobile_store.service;

import java.util.List;

import com.example.r2s_mobile_store.dto.ThumbnailDTO;
import com.example.r2s_mobile_store.exception.ThumbnailNotFoundException;

public interface ThumbnailService {
    List<ThumbnailDTO> findAll();

    ThumbnailDTO findById(long id) throws ThumbnailNotFoundException;
}
