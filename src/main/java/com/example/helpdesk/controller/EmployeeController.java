package com.example.helpdesk.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.helpdesk.model.Employee;
import com.example.helpdesk.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Employee> getEmployee(@RequestParam String id) {
        System.out.println("ID: "+id);
        return employeeService.getEmployee(id);
    }

    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Employee>> getEmployeeList() {
        return employeeService.getEmployeeList();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PostMapping("/assign-ticket")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> assignTicket(@RequestParam String id, @RequestParam String ticketNumber) {
        return employeeService.assignTicket(id, ticketNumber);
    }

    @PatchMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deleteEmployee(@RequestParam String id) {
        return employeeService.deleteEmployee(id);
    }

}