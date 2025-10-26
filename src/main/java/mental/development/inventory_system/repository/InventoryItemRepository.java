package mental.development.inventory_system.repository;

import mental.development.inventory_system.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    List<InventoryItem> findByQuantityOnHandLessThan(int quantity);
}
