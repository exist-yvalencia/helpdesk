package com.example.helpdesk.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.helpdesk.model.Ticket;
import com.example.helpdesk.model.dto.TicketDTO;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    Ticket ticketDTOtoTicket(TicketDTO dto);
    TicketDTO ticketToTicketDTO(Ticket entity);
    List<TicketDTO> ticketListToDTOs(List<Ticket> tickets);

}
