package mental.development.inventory_system.controller;

import lombok.RequiredArgsConstructor;
import mental.development.inventory_system.model.Supplier;
import mental.development.inventory_system.repository.SupplierRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierRepository repo;

    @PostMapping
    public Supplier create(@RequestBody Supplier supplier) {
        return repo.save(supplier);
    }

    @GetMapping
    public List<Supplier> all() {
        return repo.findAll();
    }
}
