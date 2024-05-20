package com.example.r2s_mobile_store.mapper;

import com.example.r2s_mobile_store.dto.OrderDetailDTO;
import com.example.r2s_mobile_store.entity.OrderDetail;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-20T07:06:32+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240417-1011, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class OrderDetailMapperImpl implements OrderDetailMapper {

    @Autowired
    private VariantProductMapper variantProductMapper;

    @Override
    public OrderDetail toEntity(OrderDetailDTO orderDetailDTO) {
        if ( orderDetailDTO == null ) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setVariantProduct( variantProductMapper.toEntity( orderDetailDTO.getVariantProductDTO() ) );
        orderDetail.setId( orderDetailDTO.getId() );
        orderDetail.setQuantity( orderDetailDTO.getQuantity() );
        orderDetail.setTotal( orderDetailDTO.getTotal() );

        return orderDetail;
    }

    @Override
    public OrderDetailDTO toDTO(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }

        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

        orderDetailDTO.setVariantProductDTO( variantProductMapper.toDTO( orderDetail.getVariantProduct() ) );
        orderDetailDTO.setId( orderDetail.getId() );
        orderDetailDTO.setQuantity( orderDetail.getQuantity() );
        orderDetailDTO.setTotal( orderDetail.getTotal() );

        return orderDetailDTO;
    }
}
