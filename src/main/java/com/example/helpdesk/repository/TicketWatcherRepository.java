package com.example.helpdesk.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.helpdesk.model.Employee;
import com.example.helpdesk.model.TicketWatcher;

import jakarta.transaction.Transactional;

@Repository
public interface TicketWatcherRepository extends JpaRepository<TicketWatcher, Integer>  {
    @Transactional
    public long deleteByEmployeeId(Employee id);
}
