package com.example.helpdesk.model;

import java.io.Serializable;
import java.util.List;

import com.example.helpdesk.model.enums.Severity;
import com.example.helpdesk.model.enums.Status;

import jakarta.persistence.CascadeType;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ticket")
@Getter @Setter @NoArgsConstructor
public class Ticket implements Serializable{
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
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
        name="ticket_watcher",
        joinColumns=@JoinColumn(name="ticket_number"),
        inverseJoinColumns=@JoinColumn(name="employee_id"))
    private List<Employee> watchers;

}