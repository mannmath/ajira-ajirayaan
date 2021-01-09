package com.ajira.ajirayaan.ajiraajirayaan;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ajira.ajirayaan.person.EmployeeEntity;
import com.ajira.ajirayaan.person.EmployeeRepository;
import com.ajira.ajirayaan.person.EmployeeService;
import com.ajira.ajirayaan.person.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplIntegrationTest {

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public EmployeeService employeeService() {
			return new EmployeeServiceImpl();
		}
	}

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	@Before
	public void setUp() {
		EmployeeEntity alex = new EmployeeEntity("alex");

		Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
	}

	@Test
	public void whenValidName_thenEmployeeShouldBeFound() {
		String name = "alex";
		EmployeeEntity found = employeeService.getEmployeeByName(name);

		assertTrue((found.getName()).equals(name));
	}
}
