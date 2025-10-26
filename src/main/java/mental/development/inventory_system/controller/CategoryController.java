package mental.development.inventory_system.controller;

import lombok.RequiredArgsConstructor;
import mental.development.inventory_system.model.Category;
import mental.development.inventory_system.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository repo;

    @PostMapping
    public Category create(@RequestBody Category category) {
        return repo.save(category);
    }

    @GetMapping
    public List<Category> all() {
        return repo.findAll();
    }
}
