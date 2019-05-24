package com.employee.service.controller;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

import com.employee.service.response.EmployeeRegisterResponse;

@Component
public class EmployeeServiceRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		restConfiguration().component("servlet")
			.bindingMode(RestBindingMode.json)
			.dataFormatProperty("prettyPrint", "true")
			.enableCORS(true)
			.port("8080")
			.contextPath("/api")
			.apiContextPath("/api-doc")
			.apiProperty("api.title", "USER - API")
			.apiProperty("api.version", "1.0.0");
		
		rest("/employee")
			.description("Get Employee service")
			.consumes("application/json")
			.produces("application/json")
			
			.post("/register")
			.description("Employee service")
			.outType(EmployeeRegisterResponse.class)
			.param()
			.name("request")
			.type(RestParamType.body)
			.dataType("EmployeeRequest")
			.name("accessToken")
			.type(RestParamType.header)
			.dataType("String")
			.endParam()
			.to("bean:empService?method=getEmployee(${header.request}, ${header.accessToken})")
			
			.post("/authorize")
			.description("AccessToken")
			.outType(String.class)
			.param()
			.name("partnerSecret")
			.type(RestParamType.header)
			.dataType("String")
			.endParam()
			.to("bean:empService?method=generateAccessToken(${header.partnerSecret})");
		
	}
	
}
