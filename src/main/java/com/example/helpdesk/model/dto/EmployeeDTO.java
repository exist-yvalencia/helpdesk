package com.example.helpdesk.model.dto;

import com.example.helpdesk.model.enums.Department;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {
    private Integer id;
    private String employeeNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private Department department;
}
