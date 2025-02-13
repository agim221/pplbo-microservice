package com.techie.microservice.order.repository;

import com.techie.microservice.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRespository extends JpaRepository<Order, Long> {
}
