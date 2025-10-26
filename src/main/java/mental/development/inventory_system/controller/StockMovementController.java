package mental.development.inventory_system.controller;

import lombok.RequiredArgsConstructor;
import mental.development.inventory_system.model.StockMovement;
import mental.development.inventory_system.repository.StockMovementRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movements")
public class StockMovementController {

    private final StockMovementRepository repo;

    @GetMapping
    public List<StockMovement> all() {
        return repo.findAll();
    }
}
