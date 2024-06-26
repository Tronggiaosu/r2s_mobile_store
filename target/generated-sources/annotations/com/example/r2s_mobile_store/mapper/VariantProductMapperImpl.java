package com.example.r2s_mobile_store.mapper;

import com.example.r2s_mobile_store.dto.VariantProductDTO;
import com.example.r2s_mobile_store.entity.VariantProduct;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-18T18:20:01+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class VariantProductMapperImpl implements VariantProductMapper {

    @Override
    public VariantProduct toEntity(VariantProductDTO variantProductDTO) {
        if ( variantProductDTO == null ) {
            return null;
        }

        VariantProduct variantProduct = new VariantProduct();

        variantProduct.setColor( variantProductDTO.getColor() );
        variantProduct.setId( variantProductDTO.getId() );
        variantProduct.setMaterial( variantProductDTO.getMaterial() );
        variantProduct.setPrice( variantProductDTO.getPrice() );
        variantProduct.setQuantity( variantProductDTO.getQuantity() );
        variantProduct.setSize( variantProductDTO.getSize() );

        return variantProduct;
    }

    @Override
    public VariantProductDTO toDTO(VariantProduct variantProduct) {
        if ( variantProduct == null ) {
            return null;
        }

        VariantProductDTO variantProductDTO = new VariantProductDTO();

        variantProductDTO.setColor( variantProduct.getColor() );
        variantProductDTO.setId( variantProduct.getId() );
        variantProductDTO.setMaterial( variantProduct.getMaterial() );
        variantProductDTO.setPrice( variantProduct.getPrice() );
        variantProductDTO.setQuantity( variantProduct.getQuantity() );
        variantProductDTO.setSize( variantProduct.getSize() );

        return variantProductDTO;
    }
}
