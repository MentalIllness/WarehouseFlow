package mental.development.inventory_system.repository;


import mental.development.inventory_system.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
