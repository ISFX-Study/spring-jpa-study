package com.study.springjpa;

import com.study.springjpa.domain.Employee;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.study.springjpa.repository.EmployeeJpaRepository;

import java.util.List;

/*
 * @DataJpaTest :  @Entiy와 스프링 데이터 JPA 레파지토리를 설정함
 */
//@DataJpaTest

/**
 * 테스트가 실행되면 스프링 부트 애플리케이션 컨텍스트가 로드되고, 스프링 데이터 JPA 레포지토리를 사용하여 테스트를 실행합니다.
 */
@SpringBootTest
class Ch08Test {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

    private Employee employee;

    /**
     * 테스트 메서드 이전에 수행함
     */
    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setName("안녕");

        employeeJpaRepository.save(employee);
    }

    @Test
    @DisplayName("스프링 데이터 JPA 사용")
    void test1() {
        employeeJpaRepository.findAll().forEach(System.out::println);
    }
}
