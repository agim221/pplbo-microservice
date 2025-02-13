package com.techie.microservice.order.service;

import com.techie.microservice.order.dto.OrderRequest;
import com.techie.microservice.order.model.Order;
import com.techie.microservice.order.repository.OrderRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRespository orderRespository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());

        orderRespository.save(order);
    }
}
