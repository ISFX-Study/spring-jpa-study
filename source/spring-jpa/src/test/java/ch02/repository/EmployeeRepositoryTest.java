package ch02.repository;

import ch02.domain.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Date;

class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @DisplayName("JPA 테스트")
    void test1() {
        System.out.println("#####");
        System.out.println(employeeRepository);

    }

    @Test
    @DisplayName("순수 JPA 테스트 - presistence.xml 사용")
    void test2() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch02");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        /**
         * JPA는 CRUD 작업은 반드시 트랜잭션 안에서 실행되어야 함
         * 트랜잭션 선언 해주지 않으면 SQL문이 실행되지 않음
         */
        // 엔티티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            // 엔티티 생성
            Employee employee = new Employee();
            employee.setName("테스트3");
            employee.setTitle("사원");
            employee.setDeptName("부서명");
            employee.setSalary(3000.0);
            employee.setStartDate(new Date());
            // 날짜가 이상하게 들어감...
            // employee.setStartDate(LocalDateTime.now());
            employee.setMemo("GenerationType.AUTO 이용");

            // 트랜잭션 시작
            tx.begin();

            em.persist(employee);

            // 트랜잭션 커밋
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 트랜잭션 롤백
            tx.rollback();
        } finally {
            // 엔티티 매니저 및 엔티티 매니저 팩토리 종료
            em.close();
            emf.close();
        }
    }
}