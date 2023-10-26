import com.study.springjpa.domain.Department;
import com.study.springjpa.domain.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

class Ch04Test {
    @Test
    @DisplayName("부서 등록")
    void test1() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com/study/springjpa/domain");

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
    @DisplayName("단방향 매핑 후 조회")
    void test2() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com/study/springjpa/domain");

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com/study/springjpa/domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

//        try {
//            Department department = em.find(Department.class, Long.valueOf("1"));
//            System.out.println("### getEmployeeList : " + department.getEmployeeList().size());
//
//            for (Employee employee : department.getEmployeeList()) {
//                System.out.println("#### employee :" + employee);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 엔티티 매니저 및 엔티티 매니저 팩토리 종료
//            em.close();
//            emf.close();
//        }
    }

    @Test
    @DisplayName("flush만 했는데 DB에 데이터 등록됨")
//  @Transactional(readOnly = true)
    void test4() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com/study/springjpa/domain");

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

//            Employee employee = em.find(Employee.class, Long.valueOf("1"));
//            employee.setMemo("flush()만 호출");

            /*
             * TODO flush()만 호출했는데 DB에 데이터 등록됨.. 왜지????
             *  =>
             */
//            em.flush();

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
    @DisplayName("CascadeType.PERSIST")
    void test5() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com/study/springjpa/domain");

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
    @DisplayName("CascadeType.REMOVE")
    void test6() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com/study/springjpa/domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 엔티티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            // 트랜잭션 시작
            tx.begin();

            Department dept = em.find(Department.class, Long.valueOf("1"));

            em.remove(dept);

            em.refresh(Department.class);

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
    @DisplayName("CascadeType.REFRESH")
    void test7() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com/study/springjpa/domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 엔티티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            // 트랜잭션 시작
            tx.begin();

            Department dept = em.find(Department.class, Long.valueOf("1"));

            dept.setDeptName("테스트");

            em.refresh(dept);

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
    @DisplayName("CascadeType.REMOVE VS orphanRemoveal 비교")
    void test8() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com/study/springjpa/domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 엔티티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            // 트랜잭션 시작
            tx.begin();

            Department dept = em.find(Department.class, Long.valueOf("1"));

//            List<Employee> employeeList = dept.getEmployeeList();

            Employee employee = em.find(Employee.class, Long.valueOf("2"));

            // Employee에서  DEPT_ID 컬럼이 NULL인 데이터는 삭제되지 않음

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
