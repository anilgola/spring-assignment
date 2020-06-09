package org.anil.controller;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.anil.model.service.TicketDto;
import org.anil.model.service.UserDto;
import org.anil.model.ui.TicketRequestModel;
import org.anil.model.ui.TicketResponseModel;
import org.anil.model.ui.UserRequestModel;
import org.anil.model.ui.UserResponseModel;
import org.anil.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeResource {

	Logger logger = LoggerFactory.getLogger(EmployeeResource.class);
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/ticket/view-ticket")
	public ResponseEntity<List<TicketResponseModel>> viewTickets(@PathVariable Long userid) {
		List<TicketDto> ticketDtoList = employeeService.getTickets();
		List<TicketResponseModel> ticketResponseModelList = ticketDtoList.stream().map( ticketDto -> {
			TicketResponseModel ticketResponseModel = new TicketResponseModel();
			BeanUtils.copyProperties(ticketDto, ticketResponseModel);
			return ticketResponseModel;
		}).collect(Collectors.toList());
		return new ResponseEntity<List<TicketResponseModel>>(ticketResponseModelList,HttpStatus.OK);
	}
	
	
	@PostMapping("/ticket/raise-ticket")
	public ResponseEntity<TicketResponseModel> submit(@RequestBody TicketRequestModel ticketUIDto,ModelMap map) {
		TicketDto TicketDto = new TicketDto();
		BeanUtils.copyProperties(ticketUIDto, TicketDto);
		TicketDto ticketDto = employeeService.raiseOrUpdateTicket(TicketDto);
		TicketResponseModel ticketResponseModel = new TicketResponseModel();
		BeanUtils.copyProperties(ticketDto, ticketResponseModel);
		return new ResponseEntity<TicketResponseModel>(ticketResponseModel,HttpStatus.CREATED);
	}
	
	
	@PostMapping("/ticket/update-ticket")
	public ResponseEntity<String> updateTicket(@RequestBody TicketRequestModel tickeRequestModel) {
		TicketDto ticketDto = new TicketDto();
		BeanUtils.copyProperties(tickeRequestModel, ticketDto);
		employeeService.updateTicket(ticketDto);
		return new ResponseEntity<String>("Ticket Updated Successfully", HttpStatus.OK);
	}

	
	@DeleteMapping("/ticket/delete-ticket/{ticketId}")
	public ResponseEntity<String> deleteTicket(@PathVariable Long ticketId) {
		employeeService.deleteTicket(ticketId);
		return new ResponseEntity<String>("Ticket Deleted Successfully:"+ticketId, HttpStatus.OK) ;
	}
	
	
	@PostMapping("/ticket/signup")
	public ResponseEntity<UserResponseModel> signup(@RequestBody UserRequestModel userRequestModel) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequestModel, userDto);
		employeeService.storeUser(userDto);
		UserResponseModel userResponseModel = new UserResponseModel();
		BeanUtils.copyProperties(userDto, userResponseModel);
		String encoding = Base64.getEncoder().encodeToString((userDto.getUsername()+":"+userDto.getPassword()).getBytes());
		userResponseModel.setAuthorizationHeader(encoding);
		return new ResponseEntity<UserResponseModel>(userResponseModel,HttpStatus.CREATED);
	}
	
	
	
}
