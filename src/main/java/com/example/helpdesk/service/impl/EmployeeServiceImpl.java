package com.example.helpdesk.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.helpdesk.model.Employee;
import com.example.helpdesk.model.dto.EmployeeDTO;
import com.example.helpdesk.model.enums.Department;
import com.example.helpdesk.model.mapper.EmployeeMapper;
import com.example.helpdesk.repository.EmployeeRepository;
import com.example.helpdesk.repository.TicketRepository;
import com.example.helpdesk.repository.TicketWatcherRepository;
import com.example.helpdesk.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TicketWatcherRepository ticketWatcherRepository;

    @Override
    public ResponseEntity<EmployeeDTO> getEmployee(String id) {
        try {
            Employee employee = employeeRepository.findById(Integer.valueOf(id)).get();
            EmployeeDTO employeeDTO = EmployeeMapper.INSTANCE.employeeToEmployeeDTO(employee);
            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> getEmployeeList(Pageable pageable) {
        try {
            List<Employee> employeeList = employeeRepository.findAll(pageable).getContent();
            List<EmployeeDTO> employeeDTOs = EmployeeMapper.INSTANCE.employeeListToDTOs(employeeList);
            return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.INSTANCE.employeeDTOtoEmployee(employeeDTO);

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

            ticketRepository.updateAssignee(Integer.parseInt(employeeId),  Integer.parseInt(ticketNumber));
            return new ResponseEntity<>("Ticket assigned to employee successfully", HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("Employee/Ticket does not exist", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.INSTANCE.employeeDTOtoEmployee(employeeDTO);
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
            Employee employee = employeeRepository.findById(Integer.valueOf(id)).get();
            
            if(ticketRepository.findByAssigneeId(Integer.valueOf(id)).size() > 0){
                throw new Exception("Deletion failed. Employee has ticket/s assigned to them.");
            }
            ticketWatcherRepository.deleteByEmployeeId(employee);
            employeeRepository.deleteById(Integer.valueOf(id));
            return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("Employee does not exist", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
        
    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> search(String text, Pageable pageable){
        List<Employee> employees = employeeRepository.findByEmployeeNumberContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrMiddleNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrDepartment(
            text, text, text, text, Department.getDepartment(text.toUpperCase()), pageable);
        List<EmployeeDTO> employeeDTOs = EmployeeMapper.INSTANCE.employeeListToDTOs(employees);
        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
    }
}
