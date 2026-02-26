package com.korber.inventory.dto;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryBatchResponse {

    private Long batchId;
    private Integer quantity;
    private LocalDate expiryDate;
}
