package com.example.helpdesk.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.helpdesk.model.Ticket;

public interface TicketService {
    public ResponseEntity<Ticket> getTicket(String id);
    public ResponseEntity<List<Ticket>> getTicketList();
    public ResponseEntity<List<Ticket>> getTicketListByAssignee(String employeeId);
    public ResponseEntity<String> createTicket(Ticket ticket);
    public ResponseEntity<String> updateTicket(Ticket ticket);
    public ResponseEntity<String> deleteTicket(String id);
}