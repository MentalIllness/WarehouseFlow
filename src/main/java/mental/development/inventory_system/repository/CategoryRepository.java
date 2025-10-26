package mental.development.inventory_system.repository;

import mental.development.inventory_system.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
