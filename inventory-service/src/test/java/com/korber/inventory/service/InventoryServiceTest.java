package com.korber.inventory.service;

import com.korber.inventory.dto.InventoryResponse;
import com.korber.inventory.entity.Inventory;
import com.korber.inventory.factory.InventoryStrategyFactory;
import com.korber.inventory.strategy.InventoryStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    @Mock
    private InventoryStrategyFactory strategyFactory;

    @Mock
    private InventoryStrategy inventoryStrategy;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void shouldReturnInventorySortedByExpiry() {

        // given
        Long productId = 1005L;

        Inventory inv1 = Inventory.builder()
                .batchId(1L)
                .productId(1005L)
                .productName("Smartwatch")
                .quantity(10)
                .expiryDate(LocalDate.of(2026, 3, 31))
                .build();

        Inventory inv2 = Inventory.builder()
                .batchId(2L)
                .productId(1005L)
                .productName("Smartwatch")
                .quantity(20)
                .expiryDate(LocalDate.of(2026, 4, 24))
                .build();

        when(strategyFactory.getStrategy("expiryDateStrategy"))
                .thenReturn(inventoryStrategy);

        when(inventoryStrategy.getInventoryByProduct(productId))
                .thenReturn(List.of(inv1, inv2));

        // when
        InventoryResponse response =
                inventoryService.getInventory(productId);

        // then
        assertEquals(1005L, response.getProductId());
        assertEquals("Smartwatch", response.getProductName());
        assertEquals(2, response.getBatches().size());

        verify(strategyFactory).getStrategy("expiryDateStrategy");
        verify(inventoryStrategy).getInventoryByProduct(productId);
    }
}
