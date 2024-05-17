package com.example.r2s_mobile_store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.r2s_mobile_store.constant.ApiUrlConstant;
import com.example.r2s_mobile_store.service.OrderDetailService;

@RestController
@RequestMapping(ApiUrlConstant.ORDER_DETAIL)
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getAllOrderDetailByOrderId(@PathVariable(value = "orderId") long orderId) {
        return ResponseEntity.ok(orderDetailService.findAllByOrderId(orderId));
    }
}
