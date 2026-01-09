package com.SpringSecurity.demo.users;

import java.util.Set;

/**
 * Safe DTO for returning user info.
 * Notice: no password hash returned.
 */
public record UserView(String userName, String email, Set<String> roles) {}
