package domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "employeeList")
@Entity
@Table(name = "S_DEPT")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DEPT_ID")
    private Long deptId;        // 부서ID

    @Column(name="DEPT_NAME", length = 25, nullable = false)
    private String deptName;    // 부서명

    // @OneToMany(mappedBy = "dept", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    // @OneToMany(mappedBy = "dept", cascade = {CascadeType.PERSIST}, orphanRemoval = true)
    @OneToMany(mappedBy = "dept", fetch = FetchType.LAZY)
    private List<Employee> employeeList = new ArrayList<>();
}