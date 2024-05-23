package com.example.helpdesk.model.dto;

import com.example.helpdesk.model.Employee;
import com.example.helpdesk.model.enums.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountDTO {
    private Integer id;
    private String username;
    private String password;
    private Role role;
    private Employee employeeId;
}
