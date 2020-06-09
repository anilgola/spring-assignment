package org.anil.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.anil.entity.Ticket;
import org.anil.model.service.TicketDto;
import org.anil.repository.TicketRepository;
import org.anil.repository.UserRepository;
import org.anil.service.EmployeeResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeResourceServiceImpl implements EmployeeResourceService {

	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<TicketDto> getTickets(Long userid) {
		List<Ticket> ticketList = ticketRepository.findTicketByUser(userid);
		List<TicketDto> ticketServiceDtoList = ticketList.stream().map(ticket -> {
			TicketDto tickerServiceDto = new TicketDto();
			BeanUtils.copyProperties(ticket, tickerServiceDto);
			
			
			
			return tickerServiceDto;
		}).collect(Collectors.toList());
		return ticketServiceDtoList;
	}

	@Override
	public TicketDto getTicketById(Long id) {
		TicketDto ticketServiceDto = new TicketDto();
		Optional<Ticket> ticketOptional = ticketRepository.findById(id);
		BeanUtils.copyProperties(ticketOptional.get(), ticketServiceDto);
		return ticketServiceDto;
		
	}

}
