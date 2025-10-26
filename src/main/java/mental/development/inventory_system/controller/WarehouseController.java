package mental.development.inventory_system.controller;

import lombok.RequiredArgsConstructor;
import mental.development.inventory_system.model.Warehouse;
import mental.development.inventory_system.repository.WarehouseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseRepository repo;

    @PostMapping
    public Warehouse create(@RequestBody Warehouse warehouse) {
        return repo.save(warehouse);
    }

    @GetMapping
    public List<Warehouse> all() {
        return repo.findAll();
    }
}
