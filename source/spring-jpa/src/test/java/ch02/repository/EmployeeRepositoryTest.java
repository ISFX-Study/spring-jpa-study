package ch02.repository;

import ch02.domain.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
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
    @DisplayName("하이버네이트 테스트 - presistence.xml 사용")
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

    @Test
    @DisplayName("Drity Checking")
    void test3() {
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
            // 엔티티 검색
            Employee employee = em.find(Employee.class, Long.valueOf(1));

            // 트랜잭션 시작
            tx.begin();

            // 엔티티 변경
            employee.setName("테스트변경");
            employee.setMemo("Drity Checking");

            // 트랜잭션 커밋
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 엔티티 매니저 및 엔티티 매니저 팩토리 종료
            em.close();
            emf.close();
        }
    }

    @Test
    @DisplayName("find")
    void test5() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch02");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        try {
            // 엔티티 검색
            Employee employee = em.find(Employee.class, Long.valueOf(1));
            System.out.println("employee = " + employee);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 엔티티 매니저 및 엔티티 매니저 팩토리 종료
            em.close();
            emf.close();
        }
    }

    @Test
    @DisplayName("find - 없는 데이터 조회한 경우")
    void test6() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch02");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        try {
            // 엔티티 검색
            Employee findEmployee = em.find(Employee.class, Long.valueOf(100));
            System.out.println("findEmployee = " + findEmployee);
       } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 엔티티 매니저 및 엔티티 매니저 팩토리 종료
            em.close();
            emf.close();
        }
    }

    @Test
    @DisplayName("분리 상태 테스트")
    void test7() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch02");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 엔티티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();
        
        try {
            // 트랜잭션 시작
            tx.begin();

            Employee employee = em.find(Employee.class, Long.valueOf(1));

            if ( em.contains(employee) ) {
                System.out.println("########## 관리 상태 ########## ");
            }
            // 분리 상태로 변경
            em.detach(employee);

            if ( !em.contains(employee) ) {
                System.out.println("########## 분리 상태 ########## ");
            }

            employee.setMemo("분리 상태로 변경");

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
    @DisplayName("삭제 상태 테스트")
    void test8() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch02");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 엔티티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            // 트랜잭션 시작
            tx.begin();

            Employee employee = new Employee();
            employee.setMemo("테스트");

            em.persist(employee);

            em.remove(employee);

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
    @DisplayName("엔티티 캐시 검색 테스트")
    void test9() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch02");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 엔티티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            // 엔티티 검색
            Employee employee1 = em.find(Employee.class, Long.valueOf(1));
            Employee employee2 = em.find(Employee.class, Long.valueOf(1));
            // 객체 주소 비교
            if ( employee1 == employee2 ) {
                System.out.println("employee1 == employee2");
            }
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
    @DisplayName("find, getReference 테스트")
    void test10() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch02");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 엔티티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            // 엔티티 검색
            Employee employee1 = em.find(Employee.class, Long.valueOf(10));
            System.out.println("employee1 : " + employee1);
            Employee employee2 = em.getReference(Employee.class, Long.valueOf(10));
            System.out.println("employee2 : " + employee2 );
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