package com.example.helpdesk.model.dto;

import java.util.List;

import com.example.helpdesk.model.Employee;
import com.example.helpdesk.model.enums.Severity;
import com.example.helpdesk.model.enums.Status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketDTO {
    private Integer ticketNumber;
    private String title;
    private String description;
    private Severity severity;
    private Status status;
    private Employee assignee;
    private List<Employee> watchers;
}
