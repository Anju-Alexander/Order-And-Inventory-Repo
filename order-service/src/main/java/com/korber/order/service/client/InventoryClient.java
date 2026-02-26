package com.korber.order.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class InventoryClient {

    private final RestTemplate restTemplate;
    private static final String INVENTORY_URL="http://localhost:8080/inventory/update";

    public void updateInventory(Long productId, Integer quantity)
    {
        Map<String, Object> request=Map.of("productId", productId,
            "quantity", quantity);

        restTemplate.postForEntity(
                INVENTORY_URL,
                request,
                Void.class
        );
    }





}
