package com.korber.inventory.strategy;

import com.korber.inventory.entity.Inventory;

import java.util.List;

public interface InventoryStrategy {

    List<Inventory> getInventoryByProduct(Long productId);
}
