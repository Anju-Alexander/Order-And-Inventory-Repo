package com.korber.inventory.controller;

import com.korber.inventory.dto.InventoryResponse;
import com.korber.inventory.dto.InventoryUpdate;
import com.korber.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{productId}")
    public InventoryResponse getInventory(@PathVariable Long productId)
    {
        return inventoryService.getInventory(productId);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateInventory(@RequestBody InventoryUpdate updateReq)
    {
        inventoryService.updateInventory(
                updateReq.getProductId(),
                updateReq.getQuantity()
        );
        return ResponseEntity.ok().build();
    }

}
