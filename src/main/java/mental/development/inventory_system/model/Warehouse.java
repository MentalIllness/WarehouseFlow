package mental.development.inventory_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // short code to identify warehouse, like "SOF-A1"
    private String code;

    // human-readable name
    private String name;

    private String address;
}

