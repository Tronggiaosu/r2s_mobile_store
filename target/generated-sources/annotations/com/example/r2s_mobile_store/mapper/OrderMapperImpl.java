package com.example.r2s_mobile_store.mapper;

import com.example.r2s_mobile_store.dto.OrderDTO;
import com.example.r2s_mobile_store.dto.OrderDetailDTO;
import com.example.r2s_mobile_store.entity.Order;
import com.example.r2s_mobile_store.entity.OrderDetail;
import com.example.r2s_mobile_store.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-18T18:06:31+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setUser( orderDTOToUser( orderDTO ) );
        order.setOrderDetails( orderDetailDTOListToOrderDetailList( orderDTO.getOrderDetailDTOs() ) );
        order.setId( orderDTO.getId() );
        order.setTotalPrice( orderDTO.getTotalPrice() );
        order.setTotalQuantity( orderDTO.getTotalQuantity() );

        return order;
    }

    @Override
    public OrderDTO toDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderDetailDTOs( orderDetailListToOrderDetailDTOList( order.getOrderDetails() ) );
        orderDTO.setUser( orderUserId( order ) );
        orderDTO.setId( order.getId() );
        orderDTO.setTotalPrice( order.getTotalPrice() );
        orderDTO.setTotalQuantity( order.getTotalQuantity() );

        return orderDTO;
    }

    protected User orderDTOToUser(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( orderDTO.getUser() );

        return user;
    }

    protected List<OrderDetail> orderDetailDTOListToOrderDetailList(List<OrderDetailDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderDetail> list1 = new ArrayList<OrderDetail>( list.size() );
        for ( OrderDetailDTO orderDetailDTO : list ) {
            list1.add( orderDetailMapper.toEntity( orderDetailDTO ) );
        }

        return list1;
    }

    protected List<OrderDetailDTO> orderDetailListToOrderDetailDTOList(List<OrderDetail> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderDetailDTO> list1 = new ArrayList<OrderDetailDTO>( list.size() );
        for ( OrderDetail orderDetail : list ) {
            list1.add( orderDetailMapper.toDTO( orderDetail ) );
        }

        return list1;
    }

    private long orderUserId(Order order) {
        if ( order == null ) {
            return 0L;
        }
        User user = order.getUser();
        if ( user == null ) {
            return 0L;
        }
        long id = user.getId();
        return id;
    }
}
