package mental.development.inventory_system.repository;

import mental.development.inventory_system.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}

