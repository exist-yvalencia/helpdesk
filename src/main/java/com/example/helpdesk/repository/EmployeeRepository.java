package com.example.helpdesk.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.helpdesk.model.Employee;
import com.example.helpdesk.model.enums.Department;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    public Employee findByEmployeeNumber(String employeeNum);
    public List<Employee> findByEmployeeNumberContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrMiddleNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrDepartment(
        String employeeNumber,
        String firstName,
        String middleName,
        String lastName,
        Department department,
        Pageable pageable
    );
}
