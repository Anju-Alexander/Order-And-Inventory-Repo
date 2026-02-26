package com.korber.inventory.service;

import com.korber.inventory.dto.InventoryBatchResponse;
import com.korber.inventory.entity.Inventory;
import com.korber.inventory.factory.InventoryStrategyFactory;
import com.korber.inventory.repository.InventoryRepository;
import com.korber.inventory.dto.InventoryResponse;
import com.korber.inventory.strategy.InventoryStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryStrategyFactory strategyFactory;
    private final InventoryRepository inventoryRepository;

    public InventoryResponse getInventory(Long productId)
    {
        InventoryStrategy strategy=strategyFactory.getStrategy("expiryDateStrategy");
        List<Inventory> inventories=strategy.getInventoryByProduct(productId);


        if (inventories.isEmpty()) {
            return InventoryResponse.builder()
                    .productId(productId)
                    .batches(List.of())
                    .build();
        }
        List<InventoryBatchResponse> inventoryBatchResponses=inventories.stream().map(inv -> InventoryBatchResponse.builder()
                .batchId(inv.getBatchId())
                .quantity(inv.getQuantity())
                .expiryDate(inv.getExpiryDate())
                .build())
                .collect(Collectors.toList());
        return InventoryResponse.builder()
                .productId(productId)
                .productName(inventories.get(0).getProductName())
                .batches(inventoryBatchResponses)
                .build();
    }

    public void updateInventory(Long productId, int quantity)
    {
        InventoryStrategy strategy=strategyFactory.getStrategy("expiryDateStrategy");
        List<Inventory> inventories=strategy.getInventoryByProduct(productId);
        int remaining=quantity;
        for(Inventory inventory: inventories)
        {
            if(remaining<=0) break;
            int available=inventory.getQuantity();
            if(available>=remaining)
            {
                inventory.setQuantity(available-remaining);
                remaining=0;
            }
            else
            {
                inventory.setQuantity(0);
                remaining-=available;
            }
        }
        inventoryRepository.saveAll(inventories);


    }
}
