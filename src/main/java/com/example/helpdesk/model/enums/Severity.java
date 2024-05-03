package com.example.helpdesk.model.enums;

public enum Severity {
    LOW("LOW"),
    NORMAL("NORMAL"),
    MAJOR("MAJOR"),
    CRITICAL("CRITICAL");

    private String severity;

    private Severity(String severity) {
        this.severity = severity;
    }

    public String getValue() {
        return this.severity;
    }
}