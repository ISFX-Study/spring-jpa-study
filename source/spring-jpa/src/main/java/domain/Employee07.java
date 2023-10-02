package domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;



@Data
@Entity
@Table(name="S_EMP07")
public class Employee07 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @GeneratedValue(strategy = GenerationType.IDENTITY) => Oracle 11g는 해당 기능이 없어서 사용 불가
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MALL_ID")
    private String maillId;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DEPT_NAME")
    private String deptName;

    @Column(name = "SALARY")
    private Double salary;

    @Column(name = "COMMISSION_PCT")
    private Double commissionPct;
}
