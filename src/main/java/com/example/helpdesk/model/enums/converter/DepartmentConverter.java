package com.example.helpdesk.model.enums.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

import com.example.helpdesk.model.enums.Department;

@Converter(autoApply = true)
public class DepartmentConverter implements AttributeConverter<Department, String> {

    @Override
    public String convertToDatabaseColumn(Department attribute) {
        if(attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public Department convertToEntityAttribute(String dbData) {
        if(dbData == null) {
            return null;
        }
        return Stream.of(Department.values())
          .filter(c -> c.getValue().equals(dbData))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
    
}
