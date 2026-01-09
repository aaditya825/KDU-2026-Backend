package com.SpringSecurity.demo.users;
import com.SpringSecurity.demo.auth.AuthService;
import com.SpringSecurity.demo.dto.RegisterRequestDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business methods for user management with method-level authorization.
 */
@Service
public class UserAdminService {

    private final AuthService authService;
    private final UserQueryService userQueryService;

    public UserAdminService(AuthService authService, UserQueryService userQueryService) {
        this.authService = authService;
        this.userQueryService = userQueryService;
    }

    /**
     * BASIC and ADMIN can view user data (read-only).
     */
    @PreAuthorize("hasAnyRole('BASIC','ADMIN')")
    public List<UserView> listUsers() {
        return userQueryService.listUsers();
    }

    /**
     * Only ADMIN can add new users.
     */
    @PreAuthorize("hasRole('ADMIN')")
    public void createUser(RegisterRequestDto request) {
        authService.register(request);
    }
}
