package mental.development.inventory_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@Entity
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventory_item_id")
    private InventoryItem inventoryItem;

    private int delta;          // +20 (stock in), -5 (stock out), etc.
    private String reason;      // "PURCHASE_ORDER", "SALE", "TRANSFER", ...
    private Instant timestamp;  // when it happened
}
