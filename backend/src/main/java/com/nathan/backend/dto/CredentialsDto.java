package com.nathan.backend.dto;

public class CredentialsDto {
    private final String username;
    private final String password;

    public CredentialsDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
