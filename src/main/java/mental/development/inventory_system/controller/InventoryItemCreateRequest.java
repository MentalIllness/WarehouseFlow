package mental.development.inventory_system.controller;

import lombok.Data;

@Data
public class InventoryItemCreateRequest {
    private Long productId;
    private Long warehouseId;
}
