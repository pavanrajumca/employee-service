package com.employee.service.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeRequest {

	@NotNull(message = "First Name cannot be empty")
	@Size(max = 50, message = "First Name length cannot be more than 50 characters")
	private String firstName;

	@NotNull(message = "Last Name cannot be empty")
	@Size(max = 50, message = "Last Name length cannot be more than 50 characters")
	private String lastName;

	@NotNull(message = "Gender cannot be empty")
	@Size(min = 1, max = 1)
	private Gender gender;

	@Size(min = 0, max = 30, message = "Email length cannot be more than 30 characters")
	private String email;

	@NotNull(message = "Mobile cannot be empty")
	@Size(min = 8, max = 18, message = "Mobile length must be within 8 to 18")
	private String mobile;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(final Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(final String mobile) {
		this.mobile = mobile;
	}

}
