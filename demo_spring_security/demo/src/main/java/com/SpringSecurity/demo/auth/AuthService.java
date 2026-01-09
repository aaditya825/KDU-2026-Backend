package com.SpringSecurity.demo.auth;
import com.SpringSecurity.demo.Exception.UserAlreadyExistsException;
import com.SpringSecurity.demo.dto.LoginRequestDto;
import com.SpringSecurity.demo.dto.RegisterRequestDto;
import com.SpringSecurity.demo.model.AppUser;
import com.SpringSecurity.demo.repository.InMemoryUserStore;
import com.SpringSecurity.demo.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

@Service
public class AuthService {

    private final InMemoryUserStore userStore;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);



    public AuthService(InMemoryUserStore userStore, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userStore = userStore;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequestDto req) {
        String username = req.userName().trim();

        if (userStore.existsByUsername(username)) {
            // Avoid leaking too much detail in real systems; for learning this is fine.
            throw new UserAlreadyExistsException("Username already exists");
        }

        // Default role if none provided (prevents accidentally creating admin users)
        Set<String> roles = (req.roles() == null || req.roles().isEmpty())
                ? Set.of("ROLE_BASIC")
                : Set.copyOf(req.roles());

        // IMPORTANT: store only the hash. BCrypt internally generates a salt.
        String passwordHash = passwordEncoder.encode(req.password());

        AppUser user = new AppUser(username, passwordHash, req.email().trim(), roles);
        userStore.save(user);
    }

    public String login(LoginRequestDto req) {
        String username = req.userName().trim();

        AppUser user = userStore.findByUsername(username)
                .orElseThrow(() -> {
                    log.warn("LOGIN_FAILED reason=USER_NOT_FOUND username={}", username);
                    return new BadCredentialsException("Invalid credentials");
                });

        if (!passwordEncoder.matches(req.password(), user.getPasswordHash())) {
            log.warn("LOGIN_FAILED reason=BAD_PASSWORD username={}", username);
            throw new BadCredentialsException("Invalid credentials");
        }

        log.info("LOGIN_SUCCESS username={} roles={}", user.getUserName(), user.getRoles());

        return jwtService.issueAccessToken(user);
    }
}
