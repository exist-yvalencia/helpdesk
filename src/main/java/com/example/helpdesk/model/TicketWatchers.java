package com.example.helpdesk.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TicketWatchers implements Serializable{
    @Column(name="ticket_number")
    private Integer ticketNumber;
    @Column(name="employee_id")
    private Integer employeeId;
}
