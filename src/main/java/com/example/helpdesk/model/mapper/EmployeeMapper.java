package com.example.helpdesk.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.helpdesk.model.Employee;
import com.example.helpdesk.model.dto.EmployeeDTO;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    
    Employee employeeDTOtoEmployee(EmployeeDTO dto);
    EmployeeDTO employeeToEmployeeDTO(Employee entity);
    List<EmployeeDTO> employeeListToDTOs(List<Employee> employees);

    // @Mapping(target = "id", ignore = true)
    // Employee createEmployeeWithoutId(EmployeeDTO entity);
}
