package com.SpringSecurity.demo.repository;
import com.SpringSecurity.demo.model.AppUser;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class InMemoryUserStore {

    // Keyed by username; ConcurrentHashMap keeps operations thread-safe.
    private final Map<String, AppUser> usersByUsername = new ConcurrentHashMap<>();

    public Optional<AppUser> findByUsername(String username) {
        return Optional.ofNullable(usersByUsername.get(username));
    }

    public boolean existsByUsername(String username) {
        return usersByUsername.containsKey(username);
    }

    public AppUser save(AppUser user) {
        usersByUsername.put(user.getUserName(), user);
        return user;
    }

    public Collection<AppUser> findAll() {
        return usersByUsername.values();
    }
}
