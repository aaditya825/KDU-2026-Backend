package com.SpringSecurity.demo.users;

import com.SpringSecurity.demo.repository.InMemoryUserStore;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Read-only querying of user data.
 * Keeps write operations separate from read operations (clean architecture).
 */
@Service
public class UserQueryService {

    private final InMemoryUserStore userStore;

    public UserQueryService(InMemoryUserStore userStore) {
        this.userStore = userStore;
    }

    public List<UserView> listUsers() {
        return userStore.findAll().stream()
                .map(u -> new UserView(u.getUserName(), u.getEmail(), u.getRoles()))
                .toList();
    }
}
