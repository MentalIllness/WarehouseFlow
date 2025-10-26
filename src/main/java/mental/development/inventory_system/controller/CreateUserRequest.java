package mental.development.inventory_system.controller;

import lombok.Data;

public @Data
class CreateUserRequest {
    private String username;
    private String password;
}
