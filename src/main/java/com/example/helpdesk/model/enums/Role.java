package com.example.helpdesk.model.enums;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    private Role(String role) {
        this.role = role;
    }

    public String getValue(){
        return this.role;
    }
}
