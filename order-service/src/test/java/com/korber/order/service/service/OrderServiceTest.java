package com.korber.order.service.service;

import com.korber.order.service.client.InventoryClient;
import com.korber.order.service.dto.OrderRequest;
import com.korber.order.service.entity.Order;
import com.korber.order.service.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private InventoryClient inventoryClient;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldPlaceOrderSuccessfully() {

        // given
        OrderRequest request = new OrderRequest();
        request.setProductId(1002L);
        request.setQuantity(3);

        Order savedOrder = Order.builder()
                .orderId(1L)
                .productId(1002L)
                .quantity(3)
                .status("PLACED")
                .build();

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);
        doNothing().when(inventoryClient).updateInventory(anyLong(), anyInt());

        // when
        var response = orderService.placeOrder(request);

        // then
        assertNotNull(response);
        assertEquals("PLACED", response.getStatus());
        assertEquals(1002L, response.getProductId());
        assertEquals(3, response.getQuantity());

        verify(inventoryClient, times(1))
                .updateInventory(1002L, 3);
        verify(orderRepository, times(1))
                .save(any(Order.class));
    }
}