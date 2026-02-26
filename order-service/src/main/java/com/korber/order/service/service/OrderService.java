package com.korber.order.service.service;

import com.korber.order.service.client.InventoryClient;
import com.korber.order.service.dto.OrderRequest;
import com.korber.order.service.dto.OrderResponse;
import com.korber.order.service.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.korber.order.service.repository.OrderRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public OrderResponse placeOrder(OrderRequest request)
    {
        //update the inventory
        inventoryClient.updateInventory(request.getProductId(),request.getQuantity());
        //save the order
        Order order=Order.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .status("PLACED")
                .orderDate(LocalDate.now())
                .build();

        Order saved=orderRepository.save(order);

        //send the response as dto
        return OrderResponse.builder()
                .orderId(saved.getOrderId())
                .productId(saved.getProductId())
                .quantity(saved.getQuantity())
                .status(saved.getStatus())
                .reservedFromBatchIds(List.of())
                .message("Order has been placed. Inventory is reserved")
                .build();

    }
}
