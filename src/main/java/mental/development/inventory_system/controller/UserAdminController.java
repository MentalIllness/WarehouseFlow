package mental.development.inventory_system.controller;

import lombok.RequiredArgsConstructor;
import mental.development.inventory_system.model.AppUser;
import mental.development.inventory_system.model.Role;
import mental.development.inventory_system.repository.AppUserRepository;
import mental.development.inventory_system.repository.RoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserAdminController {

    private final AppUserRepository userRepo;
    private final RoleRepository roleRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    // POST /api/users/create-admin
    // body: { "username": "admin", "password": "secret123" }
    @PostMapping("/create-admin")
    public AppUser createAdmin(@RequestBody CreateUserRequest body) {

        // find or create role ADMIN
        Role adminRole = roleRepo.findByName("ADMIN")
                .orElseGet(() -> {
                    Role r = new Role();
                    r.setName("ADMIN");
                    return roleRepo.save(r);
                });

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);

        AppUser user = new AppUser();
        user.setUsername(body.getUsername());
        user.setPasswordHash(passwordEncoder.encode(body.getPassword()));
        user.setRoles(roles);

        return userRepo.save(user);
    }

    // POST /api/users/create-viewer
    // body: { "username": "viewer", "password": "viewer123" }
    @PostMapping("/create-viewer")
    public AppUser createViewer(@RequestBody CreateUserRequest body) {

        Role viewerRole = roleRepo.findByName("VIEWER")
                .orElseGet(() -> {
                    Role r = new Role();
                    r.setName("VIEWER");
                    return roleRepo.save(r);
                });

        Set<Role> roles = new HashSet<>();
        roles.add(viewerRole);

        AppUser user = new AppUser();
        user.setUsername(body.getUsername());
        user.setPasswordHash(passwordEncoder.encode(body.getPassword()));
        user.setRoles(roles);

        return userRepo.save(user);
    }
}
