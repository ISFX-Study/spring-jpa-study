package domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="S_EMP_CARD")
public class EmployeeCardCh05 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cardId;

    @Column(name = "EXPIRE_DATE")
    private String expireDate;

    @OneToOne(optional = false)
    @JoinColumn(name = "EMP_CARD_ID")
    private EmployeeCh05 employeeCh05;
}
