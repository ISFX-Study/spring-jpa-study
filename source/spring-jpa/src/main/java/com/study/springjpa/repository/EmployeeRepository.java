package com.study.springjpa.repository;

import com.study.springjpa.domain.Employee;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class EmployeeRepository {
    @PersistenceContext
    private EntityManager em;

    public Employee getEmployee(Long id) {
        System.out.println("id = " + id);
        return em.find(Employee.class, id);
    }
}
