package com.example.helpdesk.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.helpdesk.model.Account;
import com.example.helpdesk.model.Employee;
import com.example.helpdesk.model.dto.AccountDTO;
import com.example.helpdesk.model.enums.Role;
import com.example.helpdesk.model.mapper.AccountMapper;
import com.example.helpdesk.repository.AccountRepository;
import com.example.helpdesk.repository.EmployeeRepository;
import com.example.helpdesk.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, EmployeeRepository employeeRepository) {
        this.accountRepository = accountRepository;
        this.employeeRepository = employeeRepository;
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
    public ResponseEntity<AccountDTO> findByEmployeeId(String employeeId) {
        try {
            Employee employee = employeeRepository.findById(Integer.valueOf(employeeId)).get();
            AccountDTO accountDto = AccountMapper.INSTANCE.accountToAccountDTO(accountRepository.findByEmployeeId(employee).get());
            return new ResponseEntity<>(accountDto, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        
    }

    @Override
    public ResponseEntity<Role> getRole(String username) {
        Account account = accountRepository.findByUsername(username).get();
        return new ResponseEntity<>(account.getRole(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(AccountDTO dto) {
        Account account = AccountMapper.INSTANCE.accountDTOtoAccount(dto);
        try {
            Account foundAccount = accountRepository.findByEmployeeId(account.getEmployeeId()).get();

            if(account.getUsername() != null && !account.getUsername().isEmpty()) {
                foundAccount.setUsername(account.getUsername());
            }

            if(account.getPassword() != null && !account.getPassword().isEmpty()){
                foundAccount.setPassword(passwordEncoder.encode(account.getPassword()));
            }

            if(account.getRole() != null) {
                foundAccount.setRole(account.getRole());
            }

            accountRepository.save(foundAccount);
            return new ResponseEntity<>("Account Updated", HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>("Account does not exist", HttpStatus.NOT_FOUND);
        }
    }
}
