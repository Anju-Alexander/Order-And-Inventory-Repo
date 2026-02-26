package com.korber.inventory.dto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {

    private Long productId;
    private String productName;
    private List<InventoryBatchResponse> batches;
}
