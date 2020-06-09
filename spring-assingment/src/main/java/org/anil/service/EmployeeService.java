package org.anil.service;

import java.util.List;

import org.anil.model.service.TicketDto;
import org.anil.model.service.UserDto;

public interface EmployeeService {
	
	List<TicketDto> getTickets();
	
	TicketDto getTicketById(Long id);
	
	TicketDto raiseOrUpdateTicket(TicketDto dto);
	
	void deleteTicket(long ticketId);

	Long getLoggedInUserId();

	String getLoggedInUserName();
	
	TicketDto updateTicket(TicketDto ticketDto);
	
	UserDto storeUser(UserDto userServiceDto);
	
	
}
