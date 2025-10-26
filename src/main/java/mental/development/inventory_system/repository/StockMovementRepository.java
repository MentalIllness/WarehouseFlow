package mental.development.inventory_system.repository;


import mental.development.inventory_system.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
}
