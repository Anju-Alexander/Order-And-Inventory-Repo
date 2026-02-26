package com.korber.inventory.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryUpdate {

    private Long productId;
    private Integer quantity;
}
