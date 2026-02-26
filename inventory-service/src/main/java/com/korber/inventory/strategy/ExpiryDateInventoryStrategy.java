package com.korber.inventory.strategy;

import com.korber.inventory.entity.Inventory;
import com.korber.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("expiryDateStrategy")
@RequiredArgsConstructor
public class ExpiryDateInventoryStrategy implements InventoryStrategy{

    private final InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> getInventoryByProduct(Long productId)
    {
        return inventoryRepository.findByProductIdOrderByExpiryDateAsc(productId);
    }
}
