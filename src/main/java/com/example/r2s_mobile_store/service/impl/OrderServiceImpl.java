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
import com.example.r2s_mobile_store.entity.VariantProduct;
import com.example.r2s_mobile_store.exception.NotFoundException;
import com.example.r2s_mobile_store.exception.OrderNotFoundException;
import com.example.r2s_mobile_store.mapper.OrderMapper;
import com.example.r2s_mobile_store.mapper.VariantProductMapper;
import com.example.r2s_mobile_store.repository.OrderDetailRepository;
import com.example.r2s_mobile_store.repository.OrderRepository;
import com.example.r2s_mobile_store.repository.UserRepository;
import com.example.r2s_mobile_store.repository.VariantProductRepository;
import com.example.r2s_mobile_store.service.OrderService;
import com.example.r2s_mobile_store.service.VariantProductService;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final VariantProductRepository variantProductRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
            OrderMapper orderMapper,
            UserRepository userRepository,
            VariantProductService variantProductService,
            VariantProductMapper variantProductMapper,
            OrderDetailRepository orderDetailRepository,
            VariantProductRepository variantProductRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userRepository = userRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.variantProductRepository = variantProductRepository;
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

        long totalQuantity = 0;
        float totalPrice = 0;
        List<OrderDetail> listOrderDetails = new ArrayList<>();
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            totalQuantity += orderDetail.getQuantity();

            orderDetail.setOrder(order);

            VariantProduct variantProductOrder = orderDetail.getVariantProduct();

            // Lấy toàn bộ thông tin của sản phâm mà khách hàng đã đặt hàng
            VariantProduct variantProduct = variantProductRepository.findById(variantProductOrder.getId())
                    .orElseThrow(() -> new NotFoundException(variantProductOrder.getId()));

            float priceProduct = variantProduct.getPrice() * orderDetail.getQuantity();

            // Tính tổng tiền của mỗi sản phẩm
            orderDetail.setTotal(priceProduct);
            listOrderDetails.add(orderDetailRepository.save(orderDetail));

            // Tính tổng tiền toàn đơn hàng
            totalPrice += priceProduct;

            // Lấy số lượng còn trong kho trừ đi số lượng mà khách đã đặt
            long stockProduct = variantProduct.getQuantity() - orderDetail.getQuantity();
            // Set lại giá trị tồn kho
            variantProduct.setQuantity(stockProduct);
            variantProduct = variantProductRepository.save(variantProduct);
        }
        // Set giá trị tổng tiền đơn hàng và tổng số lượng đơn hàng
        order.setTotalQuantity(totalQuantity);
        order.setTotalPrice(totalPrice);

        order = orderRepository.save(order);

        order.setOrderDetails(listOrderDetails);

        return orderMapper.toDTO(order);
    }
}
