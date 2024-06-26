package com.study.springjpa.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString(exclude = "employeeCh05")
@Entity
@Table(name="S_EMP_CARD")
//@SequenceGenerator(name = "employee_card_seq", sequenceName = "EMPLOYEE_CARD_SEQ", allocationSize = 1)
public class EmployeeCardCh05 {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
//    private long cardId;    // 사원증ID

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long cardId;    // 사원증ID

    @Id
    @Column(name = "CARD_ID")
    private long cardId;    // 사원증ID

    @Column(name = "EXPIRE_DATE")
    private String expireDate;

    // @MapsId // @JoinColumn으로 매핑한 외래키가 기본키 컬럼으로 사용됨
    @OneToOne
    @JoinColumn(name = "EMP_ID")
    private EmployeeCh05 employeeCh05;
}
