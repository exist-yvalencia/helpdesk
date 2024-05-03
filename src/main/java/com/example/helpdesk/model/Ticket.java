package com.example.helpdesk.model;

import java.util.List;
import java.util.stream.Collectors;

import com.example.helpdesk.model.enums.Severity;
import com.example.helpdesk.model.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="ticket")
public class Ticket {
    @Id
    @SequenceGenerator(name="ticket_ticket_number_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ticket_ticket_number_seq")
    private Integer ticketNumber;
    private String title;
    private String description;
    private Severity severity;
    private Status status;
    @ManyToOne
    @JoinColumn(name="assignee")
    private Employee assignee;
    @ManyToMany
    @JoinTable(
        name="ticket_watcher",
        joinColumns=@JoinColumn(name="ticket_number"),
        inverseJoinColumns=@JoinColumn(name="employee_id"))
    private List<Employee> watchers;

    public Ticket(String title, String description, Severity severity, Status status, Employee assignee, List<Employee> watchers) {
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.status = status;
        this.assignee = assignee;
        this.watchers = watchers;
    }

    public Ticket(Integer ticketNumber, String title, String description, Severity severity, Status status, Employee assignee, List<Employee> watchers) {
        this.ticketNumber = ticketNumber;
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.status = status;
        this.assignee = assignee;
        this.watchers = watchers;
    }

    public Ticket() {}

    public Integer getTicketNumber() {
        return this.ticketNumber;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public Severity getSeverity() {
        return this.severity;
    }

    public Status getStatus() {
        return this.status;
    }

    public Employee getAssignee() {
        return this.assignee;
    }

    public List<Employee> getWatchers() {
        return this.watchers;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAssignee(Employee assignee) {
        this.assignee = assignee;
    }

    public void setWatchers(List<Employee> watchers) {
        this.watchers = watchers;
    }

    public String toString() {
        return "Ticket {"+
        "ticket_number: "+this.ticketNumber+
        ", title: "+this.title+
        ", description: "+this.description+
        ", severity: "+this.severity.toString()+
        ", status: "+this.status.toString()+
        ", assignee: "+this.assignee.toString()+
        ", watchers: ["+this.watchers.stream().map(Object::toString).collect(Collectors.joining(","))+
        "]}";
    }

}