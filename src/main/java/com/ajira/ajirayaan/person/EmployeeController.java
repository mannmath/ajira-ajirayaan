package com.ajira.ajirayaan.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee")
	public EmployeeEntity getEmployee() {
//		EmployeeEntity employee = employeeService.getEmployeeByName("alex");
//		return new ResponseEntity(employee, HttpStatus.OK);
		return employeeService.getEmployeeByName("alex");
	}

	@GetMapping("/employee/entity")
	public ResponseEntity<EmployeeEntity> getEmployeeEntity() {
		return new ResponseEntity<EmployeeEntity>(employeeService.getEmployeeByName("alex"), HttpStatus.OK);
	}
}
