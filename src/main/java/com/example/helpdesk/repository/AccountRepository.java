package com.example.helpdesk.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.helpdesk.model.Account;
import com.example.helpdesk.model.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmployeeId(Employee employeeId);

    @Transactional
    long deleteByEmployeeId(Employee employeeId);
}
