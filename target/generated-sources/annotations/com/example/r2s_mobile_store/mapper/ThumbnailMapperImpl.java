package com.example.r2s_mobile_store.mapper;

import com.example.r2s_mobile_store.dto.ThumbnailDTO;
import com.example.r2s_mobile_store.entity.Thumbnail;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-18T18:06:31+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class ThumbnailMapperImpl implements ThumbnailMapper {

    @Override
    public Thumbnail toEntity(ThumbnailDTO thumbnailDTO) {
        if ( thumbnailDTO == null ) {
            return null;
        }

        Thumbnail thumbnail = new Thumbnail();

        thumbnail.setId( thumbnailDTO.getId() );
        thumbnail.setUrl( thumbnailDTO.getUrl() );

        return thumbnail;
    }

    @Override
    public ThumbnailDTO toDTO(Thumbnail thumbnail) {
        if ( thumbnail == null ) {
            return null;
        }

        ThumbnailDTO thumbnailDTO = new ThumbnailDTO();

        thumbnailDTO.setId( thumbnail.getId() );
        thumbnailDTO.setUrl( thumbnail.getUrl() );

        return thumbnailDTO;
    }
}
