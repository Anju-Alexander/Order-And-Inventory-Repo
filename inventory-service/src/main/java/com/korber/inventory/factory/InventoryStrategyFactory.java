package com.korber.inventory.factory;

import com.korber.inventory.strategy.InventoryStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InventoryStrategyFactory {

    private final Map<String, InventoryStrategy> strategies;


    public InventoryStrategyFactory(Map<String, InventoryStrategy> strategies)
    {
        this.strategies=strategies;
    }

    public InventoryStrategy getStrategy(String type)
    {
        return strategies.getOrDefault(type, strategies.get("expiryDateStrategy"));
    }
}
