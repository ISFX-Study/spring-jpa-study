package com.study.springjpa.repository;

import com.study.springjpa.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 스프링 데이터 JPA를 이용한 Repository
 */
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContaining(String name, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name%")
    List<Employee> findByNameQuery(@Param("name") String name);
}
