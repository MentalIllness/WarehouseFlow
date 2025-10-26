package mental.development.inventory_system.controller;

import lombok.Data;

@Data
public class CreateProductRequest {
    private String name;
    private String sku;
    private int quantity;
    private double price;
    private Long categoryId;
    private Long supplierId;
}
