package com.example.helpdesk.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
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
import com.example.helpdesk.model.dto.TicketDTO;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    } 

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<TicketDTO> getTicket(@RequestParam String id) {
        return ticketService.getTicket(id);
    }

    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<TicketDTO>> getTicketList(Pageable pageable) {
        return ticketService.getTicketList(pageable);
    }

    @GetMapping("/all/size")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Long> getListSize() {
        return ticketService.getListSize();
    }

    @GetMapping("/by-assignee")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<TicketDTO>> getTicketListByAssignee(@RequestParam String employeeId, Pageable pageable) {
        return ticketService.getTicketListByAssignee(employeeId, pageable);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createTicket(@RequestBody TicketDTO ticketDTO) {
        return ticketService.createTicket(ticketDTO);
    }

    @PatchMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateTicket(@RequestBody TicketDTO ticketDTO) {
        return ticketService.updateTicket(ticketDTO);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTicket(@RequestParam String id) {
        return ticketService.deleteTicket(id);
    }

    @GetMapping("/search")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<TicketDTO>> search(@RequestParam String text, Pageable pageable){
        return ticketService.search(text, pageable);
    }

    @GetMapping("/search/size")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Long> getSearchSize(@RequestParam String text){
        return ticketService.getSearchSize(text);
    }
}