package com.example.helpdesk.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.helpdesk.model.Ticket;
import com.example.helpdesk.repository.TicketRepository;
import com.example.helpdesk.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public ResponseEntity<Ticket> getTicket(String id) {
        try {
            return new ResponseEntity<>(ticketRepository.findById(Integer.valueOf(id)).get(), HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<Ticket>> getTicketList() {
        try{
            return new ResponseEntity<>(ticketRepository.findAll(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<Ticket>> getTicketListByAssignee(String employeeId){
        try {
            return new ResponseEntity<>(ticketRepository.getTicketListByAssignee(Integer.parseInt(employeeId)), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> createTicket(Ticket ticket) {
        try{
            ticketRepository.save(ticket);
            return new ResponseEntity<>("Ticket Created", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Ticket not created", HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    public ResponseEntity<String> updateTicket(Ticket ticket) {
        try {
            Ticket foundTicket = ticketRepository.findById(ticket.getTicketNumber()).get();

            if(ticket.getTitle() != null) {
                foundTicket.setTitle(ticket.getTitle());
            }

            if(ticket.getDescription() != null) {
                foundTicket.setDescription(ticket.getDescription());
            }

            if(ticket.getStatus() != null) {
                foundTicket.setStatus(ticket.getStatus());
            }

            if(ticket.getSeverity() != null) {
                foundTicket.setSeverity(ticket.getSeverity());
            }

            if(ticket.getAssignee() != null) {
                foundTicket.setAssignee(ticket.getAssignee());
            }

            if(ticket.getWatchers() != null || ticket.getWatchers().size() > 0) {
                foundTicket.setWatchers(ticket.getWatchers());
            }

            ticketRepository.save(foundTicket);
            return new ResponseEntity<>("Ticket updated", HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("Ticket does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> deleteTicket(String id) {
        try{
            ticketRepository.findById(Integer.valueOf(id)).get();
            ticketRepository.deleteById(Integer.valueOf(id));
            return new ResponseEntity<>("Ticket deleted", HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("Ticket does not exist", HttpStatus.NOT_FOUND);
        }
    }
    
}
