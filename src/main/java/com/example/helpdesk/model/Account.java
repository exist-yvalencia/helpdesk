package com.example.helpdesk.model;

import com.example.helpdesk.model.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="account")
@Getter @Setter @NoArgsConstructor @ToString
public class Account {
    @Id 
    @SequenceGenerator(name="account_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="account_id_seq")
    private Integer id;
    private String username;
    private String password;
    private Role role;
    @OneToOne
    @JoinColumn(name="employee_id")
    private Employee employeeId;
}
