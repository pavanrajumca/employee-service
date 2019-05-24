package com.employee.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.employee.service.EmployeeService;
import com.employee.service.request.EmployeeRequest;
import com.employee.service.response.EmployeeRegisterResponse;

@RequestMapping("/employee/service")
public class EmployeeServiceController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/register")
	public EmployeeRegisterResponse findEmployee(@RequestBody final EmployeeRequest request,
			@RequestHeader final String accessToken) {
		return employeeService.getEmployee(request, accessToken);
	}

	@PostMapping("/authorize")
	public String authorize(final String partnerAccess) {
		return employeeService.generateAcessToken(partnerAccess);
	}

}