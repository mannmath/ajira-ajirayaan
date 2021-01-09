package com.ajira.ajirayaan.ajiraajirayaan;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ajira.ajirayaan.person.EmployeeEntity;
import com.ajira.ajirayaan.person.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(com.ajira.ajirayaan.person.EmployeeController.class)
public class EmployeeRestControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private EmployeeService service;

	@Before
	public void setUp() {
		EmployeeEntity alex = new EmployeeEntity("alex");
		Mockito.when(service.getEmployeeByName(ArgumentMatchers.any())).thenReturn(alex);
	}

	@Test
	public void givenEmployee_whenGetEmployeeName_thenReturnEmployee() throws Exception {
		mvc.perform(get("/api/employee")).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", Matchers.equalTo("alex")));
	}
}
