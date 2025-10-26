package mental.development.inventory_system.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import mental.development.inventory_system.model.InventoryItem;
import mental.development.inventory_system.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inventory")
public class StockAdjustmentController {

    private final InventoryService inventoryService;

    // POST /api/inventory/1/adjust
    @PostMapping("/{inventoryItemId}/adjust")
    public InventoryItem adjust(
            @PathVariable Long inventoryItemId,
            @RequestBody AdjustStockRequest body
    ) {
        return inventoryService.adjustStock(
                inventoryItemId,
                body.getDelta(),
                body.getReason()
        );
    }

    @Data
    public static class AdjustStockRequest {
        private int delta;
        private String reason;
    }
}
