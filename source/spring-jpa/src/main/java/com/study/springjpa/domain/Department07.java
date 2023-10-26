package com.study.springjpa.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "employeeList")
@Entity
@Table(name = "S_DEPT07")
public class Department07 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DEPT_ID")
    private Long deptId;        // 부서ID

    @Column(name="DEPT_NAME", length = 25, nullable = false)
    private String deptName;    // 부서명

    @OneToMany(mappedBy = "dept")
    private List<Employee07> employeeList = new ArrayList<>();
}