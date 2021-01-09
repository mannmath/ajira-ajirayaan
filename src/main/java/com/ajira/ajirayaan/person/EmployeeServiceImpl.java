package com.ajira.ajirayaan.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeEntity getEmployeeByName(String name) {
		System.out.println("in service");
		employeeRepository.deleteAll();
		employeeRepository.save(new EmployeeEntity("alex"));
		return employeeRepository.findByName(name);
	}
}