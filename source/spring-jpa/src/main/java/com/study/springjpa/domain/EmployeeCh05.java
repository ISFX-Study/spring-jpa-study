package com.study.springjpa.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="S_EMP2")
//@SequenceGenerator(name = "employee_seq", sequenceName = "EMPLOYEE_SEQ", allocationSize = 1)
public class EmployeeCh05 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
//    private long id;

    @Column(length = 25, nullable = false)
    private String name;

    @OneToOne(mappedBy = "employeeCh05")
    private EmployeeCardCh05 employeeCardCh05;
}
