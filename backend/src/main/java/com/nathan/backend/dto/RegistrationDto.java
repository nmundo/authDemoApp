package com.nathan.backend.dto;

public class RegistrationDto extends CredentialsDto {
    private final String[] roles;

    public RegistrationDto(String username, String password, String[] roles) {
        super(username, password);
        this.roles = roles;
    }

    public String[] getRoles() {
        return roles;
    }
}
