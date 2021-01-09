package com.ajira.ajirayaan.ajiraajirayaan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.ajira.ajirayaan.person.EmployeeEntity;
import com.ajira.ajirayaan.person.EmployeeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryIntegrationTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private EmployeeRepository repo;

	@Test
	public void whenFindByName_thenReturnEmployee() {
		// given
		EmployeeEntity alex = new EmployeeEntity("alex");
		entityManager.persist(alex);
		entityManager.flush();

		// when
		EmployeeEntity found = repo.findByName(alex.getName());

		// then
		assertEquals(alex.getName(), found.getName());
	}

}
