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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

//    @Column(name = "DEPT_NAME")
//    private String deptName;

    @Column(name = "SALARY")
    private Double salary;

    @Column(name = "COMMISSION_PCT")
    private Double commissionPct;

    @ManyToOne
    @JoinColumn(name = "DEPT_ID")
    private Department07 dept;    // 부서ID

    // Department 엔티티의 컬렉션에도 Employee 엔티티를 참조하기 위해서 추가함
    public void setDept(Department07 department) {
        this.dept = department;
        if ( department != null ) {
            department.getEmployeeList().add(this);
        }
    }
}
