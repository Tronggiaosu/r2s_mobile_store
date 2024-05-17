package com.example.r2s_mobile_store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.example.r2s_mobile_store.dto.OrderDTO;
import com.example.r2s_mobile_store.entity.Order;

@Mapper(componentModel = "spring", uses = { OrderDetailMapper.class, UserMapper.class })
@Component
public interface OrderMapper {
    @Mapping(source = "orderDetailDTOs", target = "orderDetails")
    @Mapping(source = "user", target = "user.id")
    Order toEntity(OrderDTO orderDTO);

    @Mapping(source = "orderDetails", target = "orderDetailDTOs")
    @Mapping(source = "user.id", target = "user")
    OrderDTO toDTO(Order order);
}
