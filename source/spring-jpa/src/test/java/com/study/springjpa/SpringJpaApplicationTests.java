package com.study.springjpa;

import com.study.springjpa.domain.Employee;
import com.study.springjpa.repository.EmployeeJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringJpaApplicationTests {
	@Autowired
	private EmployeeJpaRepository employeeJpaRepository;

	@Test
	@DisplayName("JPA 테스트")
	void test1() {
		Employee employee = new Employee();
		employee.setName("테스트");

		employeeJpaRepository.save(employee);
	}
}
