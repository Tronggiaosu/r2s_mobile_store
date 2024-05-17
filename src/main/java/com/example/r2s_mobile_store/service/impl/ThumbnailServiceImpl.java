package com.example.r2s_mobile_store.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.r2s_mobile_store.dto.ThumbnailDTO;
import com.example.r2s_mobile_store.exception.ThumbnailNotFoundException;
import com.example.r2s_mobile_store.mapper.ThumbnailMapper;
import com.example.r2s_mobile_store.repository.ThumbnailRepository;
import com.example.r2s_mobile_store.service.ThumbnailService;

@Service
public class ThumbnailServiceImpl implements ThumbnailService {
    private final ThumbnailRepository thumbnailRepository;
    private final ThumbnailMapper thumbnailMapper;

    public ThumbnailServiceImpl(ThumbnailRepository thumbnailRepository,
            ThumbnailMapper thumbnailMapper) {
        this.thumbnailRepository = thumbnailRepository;
        this.thumbnailMapper = thumbnailMapper;
    }

    public List<ThumbnailDTO> findAll() {
        return thumbnailRepository.findAll().stream()
                .map(thumbnailMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ThumbnailDTO findById(long id) throws ThumbnailNotFoundException {
        return thumbnailMapper.toDTO(thumbnailRepository.findById(id)
                .orElseThrow(() -> new ThumbnailNotFoundException("Ảnh này không tồn tại")));
    }
}
