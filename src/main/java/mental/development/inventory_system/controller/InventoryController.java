package mental.development.inventory_system.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import mental.development.inventory_system.model.InventoryItem;
import mental.development.inventory_system.model.StockMovement;
import mental.development.inventory_system.repository.InventoryItemRepository;
import mental.development.inventory_system.repository.StockMovementRepository;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryItemRepository inventoryRepo;
    private final StockMovementRepository movementRepo;

    // PATCH /api/inventory/1/adjust
    @PatchMapping("/{id}/adjust")
    public InventoryItem adjustStock(@PathVariable Long id, @RequestBody AdjustRequest request) {

        InventoryItem item = inventoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory item not found: " + id));

        int newQty = item.getQuantityOnHand() + request.getDelta();
        item.setQuantityOnHand(newQty);
        inventoryRepo.save(item);

        StockMovement movement = new StockMovement();
        movement.setInventoryItem(item);
        movement.setDelta(request.getDelta());
        movement.setReason(request.getReason());
        movement.setTimestamp(Instant.now());
        movementRepo.save(movement);

        return item;
    }

    @Data
    public static class AdjustRequest {
        private int delta;      // +10 means adding stock, -5 means removing
        private String reason;  // e.g. "Shipment arrived" or "Customer return"
    }
}
