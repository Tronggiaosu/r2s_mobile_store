package com.example.r2s_mobile_store.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.r2s_mobile_store.dto.OrderDetailDTO;
import com.example.r2s_mobile_store.exception.OrderNotFoundException;
import com.example.r2s_mobile_store.mapper.OrderDetailMapper;
import com.example.r2s_mobile_store.repository.OrderDetailRepository;
import com.example.r2s_mobile_store.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository,
            OrderDetailMapper orderDetailMapper) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderDetailMapper = orderDetailMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderDetailDTO> findAllByOrderId(Long id) throws OrderNotFoundException {
        return orderDetailRepository.findAllByOrderId(id).stream()
                .map(orderDetailMapper::toDTO)
                .collect(Collectors.toList());
    }
}
