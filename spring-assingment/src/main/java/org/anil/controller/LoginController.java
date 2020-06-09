package org.anil.controller;

import org.anil.model.service.UserDto;
import org.anil.model.ui.UserRequestModel;
import org.anil.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/login")
	public String getLogin(ModelMap map) {
		logger.info("/login page requested");
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup(ModelMap map) {
		logger.info("/signup page requested");
		map.put("signup", new UserRequestModel());
		return "signup";
	}
	
	@PostMapping("/submit-signup")
	public String submitSignup(@ModelAttribute UserRequestModel userUIDto, ModelMap map) {
		logger.info("handler /submit-signup is called");
		UserDto userServiceDto = new UserDto();
		BeanUtils.copyProperties(userUIDto, userServiceDto);
		employeeService.storeUser(userServiceDto);
		return "redirect:login";
	}

}
