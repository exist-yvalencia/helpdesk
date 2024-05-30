package com.example.helpdesk.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.helpdesk.model.dto.AccountDTO;
import com.example.helpdesk.model.enums.Role;
import com.example.helpdesk.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Role> login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return accountService.getRole(authentication.getName());
    }

    @GetMapping("")
    @PreAuthorize("permitAll()")
    public ResponseEntity<AccountDTO> getByEmployeeId(@RequestParam String employeeId) {
        return accountService.findByEmployeeId(employeeId);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> create(@RequestBody AccountDTO dto) {
        return accountService.create(dto);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> update(@RequestBody AccountDTO dto) {
        return accountService.update(dto);
    }

}
