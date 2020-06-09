package org.anil.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.anil.entity.Ticket;
import org.anil.entity.User;
import org.anil.exception.TicketNotFoundException;
import org.anil.model.service.TicketDto;
import org.anil.model.service.UserDto;
import org.anil.repository.TicketRepository;
import org.anil.repository.UserRepository;
import org.anil.security.MyUserDetails;
import org.anil.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<TicketDto> getTickets() {
		List<Ticket> ticketList = ticketRepository.findTicketByUser(getLoggedInUserId());
		List<TicketDto> ticketDtoList = ticketList.stream().map(ticket -> {
			TicketDto tickerDto = new TicketDto();
			BeanUtils.copyProperties(ticket, tickerDto);
			return tickerDto;
		}).collect(Collectors.toList());
		return ticketDtoList;
	}

	@Override
	public TicketDto raiseOrUpdateTicket(TicketDto ticketDto) {
		Ticket ticket = new Ticket();
		BeanUtils.copyProperties(ticketDto, ticket);
		User user = new User();
		user.setId(getLoggedInUserId());
		ticket.setUser(user);
		ticket = ticketRepository.save(ticket);
		TicketDto resticketDto = new TicketDto();
		BeanUtils.copyProperties(ticket, resticketDto);
		return resticketDto;
	}
	
	@Override
	public TicketDto updateTicket(TicketDto ticketDto) {
		Ticket ticket = new Ticket();
		BeanUtils.copyProperties(ticketDto, ticket);
		User user = new User();
		user.setId(getLoggedInUserId());
		ticket.setUser(user);
		
		if(ticketRepository.findTicketByUserAndTicketId(user.getId(),ticketDto.getId()).isEmpty()) {
			throw new TicketNotFoundException("Ticket not Found with id: "+ ticketDto.getId());
		}
		ticket = ticketRepository.save(ticket);
		TicketDto resticketDto = new TicketDto();
		BeanUtils.copyProperties(ticket, resticketDto);
		return resticketDto;
	}




	@Override
	public void deleteTicket(long ticketId) {
		ticketRepository.deleteById(ticketId);
	}

	@Override
	@Cacheable("tickets")
	public TicketDto getTicketById(Long id) {
		Optional<Ticket> ticketOptional = ticketRepository.findById(id);
		TicketDto ticketDto = new TicketDto();
		BeanUtils.copyProperties(ticketOptional.get(), ticketDto);
		return ticketDto;
	}
	
	@Override
	public Long getLoggedInUserId() {
		return userRepository.getUserIdByUsername(getLoggedInUserName());
		
	}
	@Override
	public String getLoggedInUserName() {
		String username = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
			username = userDetails.getUsername();
		}
		return username;
	}

	@Override
	public UserDto storeUser(UserDto userServiceDto) {
		User user = new User();
		BeanUtils.copyProperties(userServiceDto, user);
		user.setActive(true);
		user.setRoles("ADMIN,USER");
		User savedUser = userRepository.save(user);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(savedUser, userDto);
		return userDto;
	}

}
