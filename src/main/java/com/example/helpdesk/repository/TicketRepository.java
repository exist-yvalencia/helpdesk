package com.example.helpdesk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.helpdesk.model.Ticket;
import com.example.helpdesk.model.enums.Status;

import jakarta.transaction.Transactional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query(value = "SELECT * FROM Ticket t WHERE t.assignee = ?1", nativeQuery = true)
    public List<Ticket> getTicketListByAssignee(int employeeId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Ticket SET assignee = ?1, status = ?2 WHERE ticket_number = ?3", nativeQuery = true)
    public void updateAssignee(int employeeId, String assigned , int ticketNumber);
}
