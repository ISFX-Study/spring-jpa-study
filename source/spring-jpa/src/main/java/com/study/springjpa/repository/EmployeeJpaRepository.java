package com.study.springjpa.repository;

import com.study.springjpa.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 스프링 데이터 JPA를 이용한 Repository
 */
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

}
