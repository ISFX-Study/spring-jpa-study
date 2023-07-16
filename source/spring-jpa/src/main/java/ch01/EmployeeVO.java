package ch01;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EmployeeVO {
    private long id;  // 직원아이디
    private String name; // 직원이름
    private Timestamp startDate; // 입사일
    private String title; // 직급
    private String deptName; // 부서이름
    private Double salary; // 급여
}
