package com.example.helpdesk.model;

import java.io.Serializable;

import com.example.helpdesk.model.enums.Department;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employee")
@Getter @Setter @NoArgsConstructor
public class Employee implements Serializable{
    @Id 
    @SequenceGenerator(name="employee_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_id_seq")
    private Integer id;
    private String employeeNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private Department department;

}