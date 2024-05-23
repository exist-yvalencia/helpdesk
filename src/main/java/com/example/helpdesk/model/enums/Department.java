package com.example.helpdesk.model.enums;

public enum Department {
    IT("IT"),
    ADMIN("ADMIN"),
    HR("HR"),
    SALES("SALES");

    private String department;

    private Department(String department) {
        this.department = department;
    }

    public String getValue() {
        return this.department;
    }

    public static Department getDepartment(String value) {
        switch (value) {
            case "IT":
                return IT;
            case "ADMIN":
                return ADMIN;
            case "HR":
                return HR;
            case "SALES":
                return SALES;
            default:
                return null;
        }
    }
}