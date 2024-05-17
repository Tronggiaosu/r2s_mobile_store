package com.example.r2s_mobile_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.r2s_mobile_store.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
