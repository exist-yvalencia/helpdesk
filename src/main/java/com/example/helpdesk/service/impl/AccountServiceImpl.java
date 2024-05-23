package com.example.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.helpdesk.model.Account;
import com.example.helpdesk.model.dto.AccountDTO;
import com.example.helpdesk.model.enums.Role;
import com.example.helpdesk.model.mapper.AccountMapper;
import com.example.helpdesk.repository.AccountRepository;
import com.example.helpdesk.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public ResponseEntity<String> create(AccountDTO dto) {
        Account account = AccountMapper.INSTANCE.accountDTOtoAccount(dto);
        if(!accountRepository.findByUsername(account.getUsername()).isEmpty()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
        
        try {
            account.setPassword(passwordEncoder.encode(dto.getPassword()));
            accountRepository.save(account);
            return new ResponseEntity<>("Account created", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Failed creating account", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Role> getRole(String username) {
        Account account = accountRepository.findByUsername(username).get();
        return new ResponseEntity<>(account.getRole(), HttpStatus.OK);
    }
    
}
