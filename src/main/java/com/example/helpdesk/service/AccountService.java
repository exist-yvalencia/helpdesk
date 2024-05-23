package com.example.helpdesk.service;

import org.springframework.http.ResponseEntity;

import com.example.helpdesk.model.dto.AccountDTO;
import com.example.helpdesk.model.enums.Role;

public interface AccountService {
    public ResponseEntity<String> create(AccountDTO dto);
    public ResponseEntity<Role> getRole(String username);
}
