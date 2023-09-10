package domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 엔티티 클래스
 *    - 테이블과 매핑되는 클래스
 *    - 기존 VO(Value Object)와 동일한 개념
 *    - 보통 테이블명 동일한 이름을 사용함
 * @Entity
 *    - 엔티티 클래스를 의미함
 *    - name속성을 생략하면 클래스명이 엔티티명이 됨
 * @Table
 *    - 엔티티와 매핑될 테이블을 설정
 *    - 엔티티명과 테이블명 다를경우에만 지정해주면 됨
 *      예) S_EMP -> sEmp로 하면 자동 인식함
 * @Id
 *    - PK 컬럼인 경우에만 사용
 */
@Data
@ToString(exclude = "dept")
@Entity
@Table(name="S_EMP")
//@SequenceGenerator(
//        name = "SEQ_EMP_ID" // generator명
//        , sequenceName = "S_EMP_ID" // 시퀀스명
//        , initialValue = 1 // 초기값
//        , allocationSize = 1
//)
//@TableGenerator(
//        name = "SEQ_TBL_GEN"           // generator명
//        , table = "TB_SEQ"               // 테이블명
//        , pkColumnName = "SEQ_NAME"      // PK컬럼명
//        , pkColumnValue = "EMP_SEQ"      // PK컬럼에 저장할 값
//        , valueColumnName = "NEXT_VALUE" // 실제 시퀀스
//        , initialValue = 0               // 초기값
//        , allocationSize = 1
//)
public class Employee {
    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EMP_ID")
    // @GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_TBL_GEN")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;  // 직원아이디

    @Column(length = 25, nullable = false)
    private String name; // 직원이름

    @Column(name="START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate; // 입사일

    private String title; // 직급
    private Double salary; // 급여
    private String memo;   // 비고

    /**
     * @ManyToOne : 다대일 관계 매핑
     *      optional = true : outer join
     *      optional = false : inner join
     *
     *      fetch = FetchType.EAGER : 같이 가져와
     *      fetch = FetchType.LAZY : 따로 가져와
     * @JoinColumn : 외래키 설정
     */
    @ManyToOne
    @JoinColumn(name = "DEPT_ID")
    private Department dept;    // 부서ID
}
