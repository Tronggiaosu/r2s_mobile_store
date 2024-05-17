package com.example.r2s_mobile_store.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.r2s_mobile_store.dto.ThumbnailDTO;
import com.example.r2s_mobile_store.entity.Thumbnail;

@Mapper(componentModel = "spring")
@Component
public interface ThumbnailMapper {
    Thumbnail toEntity(ThumbnailDTO thumbnailDTO);

    ThumbnailDTO toDTO(Thumbnail thumbnail);
}
