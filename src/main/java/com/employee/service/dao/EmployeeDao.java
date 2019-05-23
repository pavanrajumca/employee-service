package com.employee.service.dao;

import com.employee.service.dto.Employee;
import com.employee.service.response.EmployeeRegisterResponse;

public interface EmployeeDao {

	public EmployeeRegisterResponse findEmployee(Employee employee);

	public EmployeeRegisterResponse persist(Employee employee);

}
