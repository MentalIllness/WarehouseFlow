package mental.development.inventory_system.controller;

import lombok.RequiredArgsConstructor;
import mental.development.inventory_system.model.InventoryItem;
import mental.development.inventory_system.repository.InventoryItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final InventoryItemRepository inventoryRepo;

    // GET /api/reports/low-stock?threshold=5
    @GetMapping("/low-stock")
    public List<InventoryItem> lowStock(
            @RequestParam(defaultValue = "5") int threshold
    ) {
        return inventoryRepo.findByQuantityOnHandLessThan(threshold);
    }
}
