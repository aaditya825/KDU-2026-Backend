package com.SpringSecurity.demo.users;
import com.SpringSecurity.demo.dto.RegisterRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserAdminService userAdminService;

    public UsersController(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }

    // BASIC + ADMIN
    @GetMapping
    public ResponseEntity<List<UserView>> listUsers() {
        return ResponseEntity.ok(userAdminService.listUsers());
    }

    // ADMIN only
    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody RegisterRequestDto request) {
        userAdminService.createUser(request);
        return ResponseEntity.noContent().build();
    }
}
