package com.example.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.helpdesk.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
    @Query(value = "SELECT * FROM Employee e WHERE e.employee_number = ?1", nativeQuery = true)
    public Employee findByEmployeeNumber(String employeeNum);
}
