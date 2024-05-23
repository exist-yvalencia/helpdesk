package com.example.helpdesk.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.example.helpdesk.model.dto.TicketDTO;

public interface TicketService {
    public ResponseEntity<TicketDTO> getTicket(String id);
    public ResponseEntity<List<TicketDTO>> getTicketList(Pageable pageable);
    public ResponseEntity<List<TicketDTO>> getTicketListByAssignee(String employeeId, Pageable pageable);
    public ResponseEntity<String> createTicket(TicketDTO ticketDTO);
    public ResponseEntity<String> updateTicket(TicketDTO ticketDTO);
    public ResponseEntity<String> deleteTicket(String id);
    public ResponseEntity<List<TicketDTO>> search(String text, Pageable pageable);
}