package com.example.helpdesk.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.helpdesk.model.Employee;

public interface EmployeeService {
    public ResponseEntity<Employee> getEmployee(String id);
    public ResponseEntity<List<Employee>> getEmployeeList();
    public ResponseEntity<String> assignTicket(String id, String ticketNumber);
    public ResponseEntity<String> createEmployee(Employee employee);
    public ResponseEntity<String> updateEmployee(Employee employee);
    public ResponseEntity<String> deleteEmployee(String id);
} 
