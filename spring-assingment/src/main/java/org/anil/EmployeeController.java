package org.anil;

import java.util.List;
import java.util.stream.Collectors;

import org.anil.model.service.TicketDto;
import org.anil.model.ui.TicketRequestModel;
import org.anil.model.ui.TicketResponseModel;
import org.anil.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {
	
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/dashboard")
	public String getMyFirstJsp(ModelMap map) {
		map.put("username", employeeService.getLoggedInUserName());
		return "dashboard";
	}
	
	
	@GetMapping("/view-ticket")
	public String viewTickets(ModelMap map) {
		List<TicketDto> ticketDtoList = employeeService.getTickets();
		List<TicketResponseModel> ticketResponseModelList = ticketDtoList.stream().map( ticketDto -> {
			TicketResponseModel ticketResponseModel = new TicketResponseModel();
			BeanUtils.copyProperties(ticketDto, ticketResponseModel);
			return ticketResponseModel;
		}).collect(Collectors.toList());
		
		map.put("ticketResponseModelList", ticketResponseModelList);
		return "viewTicket";
	}
	
	@GetMapping("/raise-ticket")
	public String raiseTickets(ModelMap map) {
		map.put("ticketRequestModel", new TicketRequestModel());
		return "raiseTicket";
	}
	
	@GetMapping("/get-ticket/{id}")
	public String updateTickets(@PathVariable("id") Long id, ModelMap map) {
		TicketDto ticketDto = employeeService.getTicketById(id);
		TicketResponseModel ticketResponseModel = new TicketResponseModel();
		BeanUtils.copyProperties(ticketDto, ticketResponseModel);
		map.put("ticketResponseModel",ticketResponseModel);
		return "updateTicket";
	}
	
	
	@PostMapping("/submit-ticket")
	public String submit(@ModelAttribute TicketRequestModel ticketUIDto,ModelMap map) {
		TicketDto ticketServiceDto = new TicketDto();
		BeanUtils.copyProperties(ticketUIDto, ticketServiceDto);
		employeeService.raiseOrUpdateTicket(ticketServiceDto);
		map.put("successMessage", "Ticket Submitted successfully");
		map.put("ticketRequestModel", new TicketRequestModel());
		return "raiseTicket";
	}
	
	@PostMapping("/update-ticket")
	public String update( @ModelAttribute TicketRequestModel ticketRequestModel,ModelMap map) {
		TicketDto ticketDto = new TicketDto();
		BeanUtils.copyProperties(ticketRequestModel, ticketDto);
		employeeService.raiseOrUpdateTicket(ticketDto);
		map.put("successMessage", "Ticket Updated successfully");
		map.put("ticketResponseModel", new TicketResponseModel());
		return "updateTicket";
	}
	
}
