package mental.development.inventory_system.service;

import lombok.RequiredArgsConstructor;

import mental.development.inventory_system.model.InventoryItem;
import mental.development.inventory_system.model.StockMovement;
import mental.development.inventory_system.repository.InventoryItemRepository;
import mental.development.inventory_system.repository.StockMovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryItemRepository inventoryItemRepo;
    private final StockMovementRepository movementRepo;

    @Transactional
    public InventoryItem adjustStock(Long inventoryItemId, int delta, String reason) {
        // load InventoryItem or throw if no such row
        InventoryItem item = inventoryItemRepo.findById(inventoryItemId)
                .orElseThrow(() -> new RuntimeException("Inventory item not found"));

        // calculate new quantity
        int newQty = item.getQuantityOnHand() + delta;
        if (newQty < 0) {
            throw new RuntimeException("Not enough stock");
        }

        // update stock level
        item.setQuantityOnHand(newQty);
        inventoryItemRepo.save(item);

        // log the movement
        StockMovement movement = new StockMovement();
        movement.setInventoryItem(item);
        movement.setDelta(delta);
        movement.setReason(reason);
        movement.setTimestamp(Instant.now());
        movementRepo.save(movement);

        return item;
    }
}
