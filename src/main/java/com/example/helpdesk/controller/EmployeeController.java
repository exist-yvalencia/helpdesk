package com.example.helpdesk.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.helpdesk.model.dto.EmployeeDTO;
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
    public ResponseEntity<EmployeeDTO> getEmployee(@RequestParam String id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeList(Pageable pageable) {
        return employeeService.getEmployeeList(pageable);
    }

    @GetMapping("/all/size")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Long> getListSize() {
        return employeeService.getListSize();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @PostMapping("/assign-ticket")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> assignTicket(@RequestParam String id, @RequestParam String ticketNumber) {
        return employeeService.assignTicket(id, ticketNumber);
    }

    @PatchMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.updateEmployee(employeeDTO);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEmployee(@RequestParam String id) {
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/search")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<EmployeeDTO>> search(@RequestParam String text, Pageable pageable){
        return employeeService.search(text, pageable);
    }

    @GetMapping("/search/size")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Long> getSearchSize(@RequestParam String text){
        return employeeService.getSearchSize(text);
    }
}