package com.employee.service.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.employee.service.dto.Employee;
import com.employee.service.response.EmployeeRegisterResponse;

public class EmployeeRegisterUtils {

	private static List<Employee> employees = new ArrayList<Employee>();

	public static EmployeeRegisterResponse findEmployee(final Employee employee) {
		EmployeeRegisterResponse response = null;
		for (final Employee emp : employees) {
			if (isEqual(emp.getFirstName(), employee.getFirstName())
					&& isEqual(emp.getLastName(), employee.getLastName())
					&& isEqual(emp.getGender(), employee.getGender()) && isEqual(emp.getEmail(), employee.getEmail())
					&& isEqual(emp.getMobileNumber(), employee.getMobileNumber())) {
				response = new EmployeeRegisterResponse();
				response.setId(emp.getId());
				response.setStatus("Already exist");
			}
		}
		return response;
	}

	public static EmployeeRegisterResponse registerEmployee(final Employee employee) {
		EmployeeRegisterResponse response = null;
		final UUID uuid = UUID.randomUUID();
		response = new EmployeeRegisterResponse();
		response.setId(uuid.toString());
		response.setId("Created");
		employee.setId(uuid.toString());
		employees.add(employee);
		return response;
	}

	private static boolean isEqual(final String str1, final String str2) {
		return StringUtils.equals(str1, str2);
	}

}
