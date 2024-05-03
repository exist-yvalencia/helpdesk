package com.example.helpdesk.model.enums.converter;

import java.util.stream.Stream;

import com.example.helpdesk.model.enums.Severity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SeverityConverter implements AttributeConverter<Severity, String>  {
    @Override
    public String convertToDatabaseColumn(Severity attribute) {
        if(attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public Severity convertToEntityAttribute(String dbData) {
        if(dbData == null) {
            return null;
        }
        return Stream.of(Severity.values())
          .filter(c -> c.getValue().equals(dbData))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
}
