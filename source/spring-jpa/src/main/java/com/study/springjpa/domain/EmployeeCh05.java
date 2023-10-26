package com.study.springjpa.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="S_EMP2")
public class EmployeeCh05 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 25, nullable = false)
    private String name;

    @OneToOne(mappedBy = "employeeCh05")
    private EmployeeCardCh05 employeeCardCh05;
}
