package com.example.helpdesk.model.enums;

public enum Status {
    NEW("NEW"),
    ASSIGNED("ASSIGNED"),
    INPROGRESS("INPROGRESS"),
    CLOSED("CLOSED");

    private String status;

    private Status(String status) {
        this.status = status;
    }

    public String getValue() {
        return this.status;
    }
}