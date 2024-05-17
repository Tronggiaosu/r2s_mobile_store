package com.example.r2s_mobile_store.service;

import java.util.List;

import com.example.r2s_mobile_store.dto.OrderDTO;
import com.example.r2s_mobile_store.exception.OrderNotFoundException;

public interface OrderService {
    List<OrderDTO> findAll();

    OrderDTO findById(long id) throws OrderNotFoundException;

    OrderDTO create(OrderDTO orderDTO);
}
