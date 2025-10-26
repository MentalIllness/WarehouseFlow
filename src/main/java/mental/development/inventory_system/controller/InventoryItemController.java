package mental.development.inventory_system.controller;

import lombok.RequiredArgsConstructor;
import mental.development.inventory_system.model.InventoryItem;
import mental.development.inventory_system.repository.InventoryItemRepository;
import mental.development.inventory_system.repository.ProductRepository;
import mental.development.inventory_system.repository.WarehouseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inventory-items")
public class InventoryItemController {

    private final InventoryItemRepository inventoryRepo;
    private final ProductRepository productRepo;
    private final WarehouseRepository warehouseRepo;

    // body: { "productId": 1, "warehouseId": 1 }
    @PostMapping
    public InventoryItem create(@RequestBody InventoryItemCreateRequest body) {
        var product = productRepo.findById(body.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        var warehouse = warehouseRepo.findById(body.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        InventoryItem item = new InventoryItem();
        item.setProduct(product);
        item.setWarehouse(warehouse);
        item.setQuantityOnHand(0);
        item.setQuantityReserved(0);
        return inventoryRepo.save(item);
    }

    @GetMapping
    public List<InventoryItem> all() {
        return inventoryRepo.findAll();
    }

    @GetMapping("/{id}")
    public InventoryItem getOne(@PathVariable Long id) {
        return inventoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory item not found"));
    }
}
