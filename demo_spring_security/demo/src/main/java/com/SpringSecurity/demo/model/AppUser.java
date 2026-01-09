package com.SpringSecurity.demo.model;

import java.util.Set;


public class AppUser {
    private final String userName;
    private final String passwordHash;
    private final String email;
    private final Set<String> roles;

    public AppUser(String userName, String passwordHash, String email, Set<String> roles) {
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.email = email;
        this.roles = roles;
        System.out.println(this.toString());
    }



    @Override
    public String toString() {
        return "AppUser{" +
                "userName='" + userName + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }

    public String getUserName() { return userName; }
    public String getPasswordHash() { return passwordHash; }
    public String getEmail() { return email; }
    public Set<String> getRoles() { return roles; }
}
