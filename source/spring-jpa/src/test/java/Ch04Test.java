import domain.Department;
import domain.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

class Ch04Test {
    @Test
    @DisplayName("부서 등록 - 4회차 이슈")
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

            Department dept = new Department();
            dept.setDeptName("개발1팀");
            em.persist(dept);

            Employee employee = new Employee();
            employee.setName("개발자1");
            employee.setDept(dept);
            em.persist(employee);

            Employee employee2 = new Employee();
            employee2.setName("개발자2");
            employee2.setDept(dept);
            em.persist(employee2);

            // TODO flush() -> commit() 호출 안 했는데 데이터 등록됨.. 왜지????
            em.flush();

            // 트랜잭션 커밋
            // tx.commit();
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
    @DisplayName("단방향 매핑 후 조회")
    void test2() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        try {
            /**
             * @ManyToOne(optional = true) : outer join
             * @ManyToOne(optional = false) : inner join
             */
            Employee employee = em.find(Employee.class, Long.valueOf("1"));
            System.out.println("### employee : " + employee);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 엔티티 매니저 및 엔티티 매니저 팩토리 종료
            em.close();
            emf.close();
        }
    }

    @Test
    @DisplayName("양방향 매핑 후 조회")
    void test3() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        try {
            Department department = em.find(Department.class, Long.valueOf("1"));
            System.out.println("### getEmployeeList : " + department.getEmployeeList().size());

            for (Employee employee : department.getEmployeeList()) {
                System.out.println("#### employee :" + employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 엔티티 매니저 및 엔티티 매니저 팩토리 종료
            em.close();
            emf.close();
        }
    }
}
