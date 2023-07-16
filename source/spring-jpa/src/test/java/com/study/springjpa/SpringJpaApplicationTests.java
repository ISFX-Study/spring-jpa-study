package com.study.springjpa;

import ch02.domain.Employee;
import ch02.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Rollback(false)
class SpringJpaApplicationTests {
	@Autowired
    EmployeeRepository employeeRepository;

	@Test
	void contextLoads() {

	}

	@Test
	@DisplayName("JPA 테스트")
	@Transactional
	void test1() {
		EmployeeRepository employeeRepository = new EmployeeRepository();

		Long id = Long.valueOf("2");
		Employee employee = employeeRepository.getEmployee(id);

		Assertions.assertThat(employee.getId()).isEqualTo(id);
	}
}
