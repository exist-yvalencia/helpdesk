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
    // public String toString() {
    //     switch(this) {
    //         case IT:
    //             return "IT";
    //         case ADMIN:
    //             return "ADMIN";
    //         case HR:
    //             return "HR";
    //         case SALES:
    //             return "SALES";
    //         default:
    //             return null;
    //     }
    // }

    // public static Department of(String department) {
    //     switch(department) {
    //         case "IT":
    //             return IT;
    //         case "ADMIN":
    //             return ADMIN;
    //         case "HR":
    //             return HR;
    //         case "SALES":
    //             return SALES;
    //         default:
    //             return null;
    //     }
    // }
}