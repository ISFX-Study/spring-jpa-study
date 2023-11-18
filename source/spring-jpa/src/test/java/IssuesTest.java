import com.study.springjpa.domain.Department;
import com.study.springjpa.domain.Employee;
import com.study.springjpa.domain.EmployeeCardCh05;
import com.study.springjpa.domain.EmployeeCh05;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class IssuesTest {
    @Test
    @DisplayName("데이터 등록")
    void loadData() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 엔티티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            // 트랜잭션 시작
            tx.begin();

            Department dept = new Department();
            dept.setDeptName("개발1팀");

            List<Employee> employeeList = new ArrayList<>();

            for (int i=0; i<=5; i++) {
                Employee employee = new Employee();
                employee.setName("개발자" + i);
                employee.setMemo("테스트" + i);
                employee.setDept(dept);
                employeeList.add(i, employee);
            }

            dept.setEmployeeList(employeeList);

            em.persist(dept);

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

    @Test
    @DisplayName("[6회차] 시퀀스 오류")
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

            // EmployeeCh05 ID와 같은 값으로 수동으로 넣어줌
            card.setCardId(employee.getId());

            em.persist(card);

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

    @Test
    @DisplayName("[5회차] orphanRemoval = true 이슈")
    void test2() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 엔티티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            // 트랜잭션 시작
            tx.begin();

            // 방법1)
//            Department dept = em.find(Department.class, Long.valueOf("1"));
//            em.remove(dept);

            // 방법2)
            Department dept = em.find(Department.class, Long.valueOf("1"));

            for (Employee employee : dept.getEmployeeList()) {
                employee.setDept(null);
            }

            em.remove(dept);

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

