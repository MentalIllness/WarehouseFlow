package mental.development.inventory_system.controller;

import lombok.RequiredArgsConstructor;
import mental.development.inventory_system.model.Category;
import mental.development.inventory_system.model.Product;
import mental.development.inventory_system.model.Supplier;
import mental.development.inventory_system.repository.CategoryRepository;
import mental.development.inventory_system.repository.ProductRepository;
import mental.development.inventory_system.repository.SupplierRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final SupplierRepository supplierRepo;

    @PostMapping
    public Product create(@RequestBody CreateProductRequest body) {

        Category cat = categoryRepo.findById(body.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found: " + body.getCategoryId()));

        Supplier sup = supplierRepo.findById(body.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found: " + body.getSupplierId()));

        Product p = new Product();
        p.setName(body.getName());
        p.setSku(body.getSku());
        p.setQuantity(body.getQuantity());
        p.setPrice(body.getPrice());
        p.setCategory(cat);
        p.setSupplier(sup);

        return productRepo.save(p);
    }

    // GET /api/products?query=thinkpad&page=0&size=20
    @GetMapping
    public Page<Product> all(
            @RequestParam(defaultValue = "") String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return productRepo.findByNameContainingIgnoreCase(query, PageRequest.of(page, size));
    }
}
