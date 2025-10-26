package mental.development.inventory_system.controller;

import lombok.Data;

@Data
public class AdjustStockRequest {
    private int delta;
    private String reason;
}
