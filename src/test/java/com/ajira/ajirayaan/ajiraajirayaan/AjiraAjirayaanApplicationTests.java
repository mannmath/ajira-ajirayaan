package com.ajira.ajirayaan.ajiraajirayaan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ajira.ajirayaan.person.EmployeeEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AjiraAjirayaanApplicationTests {

	@LocalServerPort
	private int port;

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders httpHeaders = new HttpHeaders();

	@Test
	public void givenUserName_whenUserName_thenReturnEmployee() throws Exception {
		HttpEntity<EmployeeEntity> entity = new HttpEntity<EmployeeEntity>(null, httpHeaders);
		ResponseEntity<EmployeeEntity> response = restTemplate.exchange(createURLWithPort("/api/employee/entity"),
				HttpMethod.GET, entity, EmployeeEntity.class);

		String actual = response.getBody().getName();
		assertEquals("alex", actual);
	}
}
