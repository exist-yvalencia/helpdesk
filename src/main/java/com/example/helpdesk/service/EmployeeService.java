package com.example.helpdesk.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.example.helpdesk.model.dto.EmployeeDTO;

public interface EmployeeService {
    public ResponseEntity<EmployeeDTO> getEmployee(String id);
    public ResponseEntity<List<EmployeeDTO>> getEmployeeList(Pageable pageable);
    public ResponseEntity<Long> getListSize();
    public ResponseEntity<String> assignTicket(String id, String ticketNumber);
    public ResponseEntity<EmployeeDTO> createEmployee(EmployeeDTO employeeDTO);
    public ResponseEntity<String> updateEmployee(EmployeeDTO employeeDTO);
    public ResponseEntity<String> deleteEmployee(String id);
    public ResponseEntity<List<EmployeeDTO>> search(String text, Pageable pageable);
    public ResponseEntity<Long> getSearchSize(String text);
} 
