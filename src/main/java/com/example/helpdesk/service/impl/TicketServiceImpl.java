package com.example.helpdesk.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.helpdesk.model.Ticket;
import com.example.helpdesk.model.dto.TicketDTO;
import com.example.helpdesk.model.enums.Severity;
import com.example.helpdesk.model.enums.Status;
import com.example.helpdesk.model.mapper.TicketMapper;
import com.example.helpdesk.repository.TicketRepository;
import com.example.helpdesk.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TicketMapper ticketMapper;

    @Override
    public ResponseEntity<TicketDTO> getTicket(String id) {
        try {
            Ticket ticket = ticketRepository.findById(Integer.valueOf(id)).get();
            TicketDTO ticketDTO = ticketMapper.ticketToTicketDTO(ticket);
            return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<TicketDTO>> getTicketList(Pageable pageable) {
        try{
            List<Ticket> ticketList = ticketRepository.findAll(pageable).getContent();
            List<TicketDTO> ticketDTOs = ticketMapper.ticketListToDTOs(ticketList);
            return new ResponseEntity<>(ticketDTOs, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<TicketDTO>> getTicketListByAssignee(String employeeId, Pageable pageable){
        try {
            List<Ticket> ticketList = ticketRepository.findByAssigneeId(Integer.parseInt(employeeId), pageable);
            List<TicketDTO> ticketDTOs = ticketMapper.ticketListToDTOs(ticketList);
            return new ResponseEntity<>(ticketDTOs, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Long> getListSize() {
        long size = ticketRepository.count();
        return new ResponseEntity<>(Long.valueOf(size), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.ticketDTOtoTicket(ticketDTO);
        try{
            ticketRepository.save(ticket);
            return new ResponseEntity<>("Ticket Created", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Ticket not created", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> updateTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.ticketDTOtoTicket(ticketDTO);
        try {
            Ticket foundTicket = ticketRepository.findById(ticket.getTicketNumber()).get();

            if(ticket.getTitle() != null && !ticket.getTitle().isEmpty()) {
                foundTicket.setTitle(ticket.getTitle());
            }

            if(ticket.getDescription() != null && !ticket.getDescription().isEmpty()) {
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

    private int textToTicketNumber(String text) {
        int ticketNumber;
        try {
            ticketNumber = Integer.parseInt(text);
        } catch(Exception e) {
            ticketNumber = 0;
        }
        return ticketNumber;
    }
    
    @Override
    public ResponseEntity<List<TicketDTO>> search(String text, Pageable pageable) {
        List<Ticket> tickets = ticketRepository.findByTicketNumberOrTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrSeverityOrStatus(
            textToTicketNumber(text), 
            text, 
            text, 
            Severity.getSeverity(text.toUpperCase()), 
            Status.geStatus(text.toUpperCase()), 
            pageable);

        List<TicketDTO> ticketDTOs = TicketMapper.INSTANCE.ticketListToDTOs(tickets);
        return new ResponseEntity<>(ticketDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Long> getSearchSize(String text) {
        Long size = ticketRepository.countByTicketNumberOrTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrSeverityOrStatus(
            textToTicketNumber(text), text, text, Severity.getSeverity(text.toUpperCase()), Status.geStatus(text.toUpperCase()));
        return new ResponseEntity<>(size, HttpStatus.OK);
    }
}
