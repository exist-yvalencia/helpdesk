package com.example.helpdesk.model;

import com.example.helpdesk.model.enums.Department;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
    @Id 
    @SequenceGenerator(name="employee_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_id_seq")
    private Integer id;
    private String employeeNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private Department department;


    public Employee(String employeeNumber, String firstName, String middleName, String lastName, Department department) {
        this.employeeNumber = employeeNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.department = department;
    }

    public Employee(Integer id, String employeeNumber, String firstName, String middleName, String lastName, Department department) {
        this.id = id;
        this.employeeNumber = employeeNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.department = department;
    }

    public Employee() {}

    public Integer getId(){
        return this.id;
    }

    public String getEmployeeNumber() {
        return this.employeeNumber;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    // @PrePersist
    // void fillPesistent() {
    //     if(department != null) {
    //         this.departmentValue = department.toString();
    //     }
    // }

    // @PostLoad
    // void fillTransient() {
    //     if(!departmentValue.isEmpty()) {
    //         this.department = Department.of(departmentValue);
    //     }
    // }

    public String toString() {
        return "Employee {"+
        "id: "+this.id +
        ", employee_number: "+this.employeeNumber+
        ", first_name: "+this.firstName+
        ", middle_name: "+this.middleName+
        ", last_name: "+this.lastName+
        ", department: "+this.department.toString()+
        "}";
    }
}