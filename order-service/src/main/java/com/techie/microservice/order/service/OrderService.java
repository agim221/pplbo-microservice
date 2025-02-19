package com.techie.microservice.order.service;

import com.techie.microservice.order.client.InventoryClient;
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
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {
        var isProductInStock =  inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            orderRespository.save(order);
       } else {
           throw new RuntimeException("Product with SkuCode " + orderRequest.skuCode() + " is not in stock");
       }
    }
}
