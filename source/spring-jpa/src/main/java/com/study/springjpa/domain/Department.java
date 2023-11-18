package com.study.springjpa.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "employeeList")
@Entity
@Table(name = "S_DEPT")
@NamedNativeQuery(name = "Department.searchDeptAll" , query = "SELECT * FROM S_DEPT S")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DEPT_ID")
    private Long deptId;        // 부서ID

    @Column(name="DEPT_NAME", length = 25, nullable = false)
    private String deptName;    // 부서명

//    @OneToMany(mappedBy = "dept", cascade = { CascadeType.PERSIST }, orphanRemoval = true)
    @OneToMany(mappedBy = "dept", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
    private List<Employee> employeeList = new ArrayList<Employee>();
}