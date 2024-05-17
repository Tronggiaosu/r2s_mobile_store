package com.example.r2s_mobile_store.service;

import java.util.List;

import com.example.r2s_mobile_store.dto.OrderDetailDTO;
import com.example.r2s_mobile_store.exception.OrderNotFoundException;

public interface OrderDetailService {
    List<OrderDetailDTO> findAllByOrderId(Long id) throws OrderNotFoundException;
}
