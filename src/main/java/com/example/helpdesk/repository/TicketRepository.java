package com.example.helpdesk.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.helpdesk.model.Ticket;
import com.example.helpdesk.model.enums.Severity;
import com.example.helpdesk.model.enums.Status;

import jakarta.transaction.Transactional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    public List<Ticket> findByAssigneeId(int employeeId, Pageable pageable);
    public List<Ticket> findByAssigneeId(int employeeId);
    public List<Ticket> findByTicketNumberOrTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrSeverityOrStatus(
        int ticketNumber,
        String title,
        String description,
        Severity severity,
        Status status,
        Pageable pageable
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE Ticket SET assignee = ?1 WHERE ticket_number = ?2", nativeQuery = true)
    public void updateAssignee(int employeeId, int ticketNumber);
}
