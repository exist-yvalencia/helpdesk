package com.example.helpdesk.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.helpdesk.model.Employee;
import com.example.helpdesk.model.enums.Status;
import com.example.helpdesk.repository.EmployeeRepository;
import com.example.helpdesk.repository.TicketRepository;
import com.example.helpdesk.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    TicketRepository ticketRepository;
    Logger logger;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, TicketRepository ticketRepository) {
        this.employeeRepository = employeeRepository;
        this.ticketRepository = ticketRepository;
        this.logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    }

    @Override
    public ResponseEntity<Employee> getEmployee(String id) {
        try {
            logger.debug("getEmployee("+id+")");
            return new ResponseEntity<>(employeeRepository.findById(Integer.valueOf(id)).get(), HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployeeList() {
        try {
            logger.debug("getEmployeeList()");
            return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> createEmployee(Employee employee) {
        if(employeeRepository.findByEmployeeNumber(employee.getEmployeeNumber()) == null) {
            employeeRepository.save(employee);
            return new ResponseEntity<>("Employee saved", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Employee already exists", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> assignTicket(String employeeId, String ticketNumber) {
        try {
            employeeRepository.findById(Integer.valueOf(employeeId)).get();
            ticketRepository.findById(Integer.valueOf(ticketNumber)).get();

            ticketRepository.updateAssignee(Integer.parseInt(employeeId), Status.ASSIGNED.getValue(), Integer.parseInt(ticketNumber));
            return new ResponseEntity<>("Ticket assigned to employee successfully", HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("Employee/Ticket does not exist", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> updateEmployee(Employee employee) {
        try {
            Employee foundEmployee = employeeRepository.findById(employee.getId()).get();

            if(employee.getEmployeeNumber() != null && !employee.getEmployeeNumber().isEmpty()) {
                foundEmployee.setEmployeeNumber(employee.getEmployeeNumber());
            }

            if(employee.getFirstName() != null && !employee.getFirstName().isEmpty()) {
                foundEmployee.setFirstName(employee.getFirstName());
            }

            if(employee.getMiddleName() != null && !employee.getMiddleName().isEmpty()) {
                foundEmployee.setMiddleName(employee.getMiddleName());
            }

            if(employee.getLastName() != null && !employee.getLastName().isEmpty()) {
                foundEmployee.setLastName(employee.getLastName());
            }

            if(employee.getDepartment() != null) {
                foundEmployee.setDepartment(employee.getDepartment());
            }

            employeeRepository.save(foundEmployee);
            return new ResponseEntity<>("Employee updated", HttpStatus.OK);
        
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("Employee does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> deleteEmployee(String id) {
        try {
            employeeRepository.findById(Integer.valueOf(id)).get();
            
            if(ticketRepository.getTicketListByAssignee(Integer.valueOf(id)).size() > 0){
                throw new Exception("Deletion failed. Employee has ticket/s assigned to them.");
            }
            
            employeeRepository.deleteById(Integer.valueOf(id));
            return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("Employee does not exist", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
        
    }
}
