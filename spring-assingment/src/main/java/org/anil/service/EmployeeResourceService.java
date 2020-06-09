package org.anil.service;

import java.util.List;

import org.anil.model.service.TicketDto;

public interface EmployeeResourceService {

	
	List<TicketDto> getTickets(Long userid);
	
	TicketDto getTicketById(Long id);
	
}
