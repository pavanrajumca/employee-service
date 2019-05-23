package com.employee.service.dao;

import org.springframework.stereotype.Component;

import com.employee.service.dto.Employee;
import com.employee.service.response.EmployeeRegisterResponse;
import com.employee.service.util.EmployeeRegisterUtils;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public EmployeeRegisterResponse findEmployee(final Employee employee) {

		return EmployeeRegisterUtils.findEmployee(employee);
	}

	@Override
	public EmployeeRegisterResponse persist(final Employee employee) {

		return EmployeeRegisterUtils.registerEmployee(employee);
	}

}
