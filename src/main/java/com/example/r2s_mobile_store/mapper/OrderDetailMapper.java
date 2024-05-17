package com.example.r2s_mobile_store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.example.r2s_mobile_store.dto.OrderDetailDTO;
import com.example.r2s_mobile_store.entity.OrderDetail;

@Mapper(componentModel = "spring", uses = { VariantProductMapper.class })
@Component
public interface OrderDetailMapper {
    @Mapping(source = "variantProductDTO", target = "variantProduct")
    OrderDetail toEntity(OrderDetailDTO orderDetailDTO);

    @Mapping(source = "variantProduct", target = "variantProductDTO")
    OrderDetailDTO toDTO(OrderDetail orderDetail);
}
