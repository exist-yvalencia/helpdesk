package com.example.helpdesk.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.helpdesk.service.TicketService;
import com.example.helpdesk.model.Ticket;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    } 

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Ticket> getTicket(@RequestParam String id) {
        return ticketService.getTicket(id);
    }

    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Ticket>> getTicketList() {
        return ticketService.getTicketList();
    }

    @GetMapping("/by-assignee")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Ticket>> getTicketListByAssignee(@RequestParam String employeeId) {
        return ticketService.getTicketListByAssignee(employeeId);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    @PatchMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateTicket(@RequestBody Ticket ticket) {
        return ticketService.updateTicket(ticket);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTicket(@RequestParam String id) {
        return ticketService.deleteTicket(id);
    }
}