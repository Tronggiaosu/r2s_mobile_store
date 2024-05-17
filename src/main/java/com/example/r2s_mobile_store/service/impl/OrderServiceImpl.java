package com.example.r2s_mobile_store.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.r2s_mobile_store.dto.OrderDTO;
import com.example.r2s_mobile_store.entity.Order;
import com.example.r2s_mobile_store.entity.OrderDetail;
import com.example.r2s_mobile_store.entity.User;
import com.example.r2s_mobile_store.exception.NotFoundException;
import com.example.r2s_mobile_store.exception.OrderNotFoundException;
import com.example.r2s_mobile_store.mapper.OrderMapper;
import com.example.r2s_mobile_store.mapper.VariantProductMapper;
import com.example.r2s_mobile_store.repository.OrderDetailRepository;
import com.example.r2s_mobile_store.repository.OrderRepository;
import com.example.r2s_mobile_store.repository.UserRepository;
import com.example.r2s_mobile_store.service.OrderService;
import com.example.r2s_mobile_store.service.VariantProductService;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
            OrderMapper orderMapper,
            UserRepository userRepository,
            VariantProductService variantProductService,
            VariantProductMapper variantProductMapper,
            OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userRepository = userRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public OrderDTO findById(long id) throws OrderNotFoundException {
        return orderMapper.toDTO(orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Không tồn tại đơn hàng này")));
    }

    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUser())
                .orElseThrow(() -> new NotFoundException(orderDTO.getUser()));
        Order order = orderMapper.toEntity(orderDTO);
        order.setUser(user);

        order = orderRepository.save(order);

        List<OrderDetail> listOrderDetails = new ArrayList<>();
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            orderDetail.setOrder(order);
            listOrderDetails.add(orderDetailRepository.save(orderDetail));

        }
        order.setOrderDetails(listOrderDetails);

        return orderMapper.toDTO(order);
    }
}
