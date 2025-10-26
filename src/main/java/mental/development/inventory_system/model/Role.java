package mental.development.inventory_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // example values: "ADMIN", "VIEWER"
    @Column(unique = true, nullable = false)
    private String name;
}
