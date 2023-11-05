package com.study.springjpa;

import com.study.springjpa.domain.Employee;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.study.springjpa.repository.EmployeeJpaRepository;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
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
//    @BeforeEach
    void setUp() {
        for (int i = 0; i < 10; i++) {
            employee = new Employee();
            employee.setName("테스트" + i);
            employeeJpaRepository.save(employee);
        }
    }

    @Test
    @DisplayName("스프링 데이터 JPA 사용")
    void test1() {
        employeeJpaRepository.findAll().forEach(System.out::println);
    }

    @Test
    @DisplayName("페이징")
    void test2() {
        // 첫 페이지에 2개씩 가져옴
        Pageable pageable = (Pageable) PageRequest.ofSize(2).withPage(0);
        List<Employee> list = employeeJpaRepository.findByNameContaining("테스트", pageable);

        list.forEach(System.out::println);
    }

    @Test
    @DisplayName("@Query")
    void test3() {
        List<Employee> list = employeeJpaRepository.findByNameQuery("테스트");

        list.forEach(System.out::println);
    }
}
