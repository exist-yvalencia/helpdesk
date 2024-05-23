package com.example.helpdesk.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.helpdesk.model.Account;
import com.example.helpdesk.model.dto.AccountDTO;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    
    Account accountDTOtoAccount(AccountDTO accountDTO);
    AccountDTO accountToAccountDTO(Account account);
}
