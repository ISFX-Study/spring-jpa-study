import domain.Department;
import domain.Employee;
import domain.EmployeeCardCh05;
import domain.EmployeeCh05;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ch05Test {
    @Test
    @DisplayName("일대일 단방향 등록 및 조회")
    void test1() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 엔티티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            // 트랜잭션 시작
            tx.begin();

            // 직원
            EmployeeCh05 employee = new EmployeeCh05();
            employee.setName("직원1");
            em.persist(employee);
            
            // 사원증
            EmployeeCardCh05 card = new EmployeeCardCh05();
            card.setExpireDate("2023-09-11");
            card.setEmployeeCh05(employee);
            em.persist(card);

            em.flush();

            EmployeeCardCh05 findcard = em.find(EmployeeCardCh05.class, Long.valueOf("2"));
            System.out.println("findcard : " + findcard);
            System.out.println("사원증번호 : " + findcard.getCardId());
            System.out.println("직원명 : " + findcard.getEmployeeCh05().getName());

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
