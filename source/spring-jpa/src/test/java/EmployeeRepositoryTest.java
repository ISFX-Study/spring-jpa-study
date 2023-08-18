import repository.EmployeeRepository;
import domain.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

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

    @Test
    @DisplayName("엔티티 매니저 비교")
    void test11() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

        EntityManager em = null;
        EntityManager em2=  null;
        try {
            // 엔티티 매니저 생성
            em = emf.createEntityManager();
            em2 = emf.createEntityManager();

            System.out.println("em : " + em);
            System.out.println("em2 : " + em2);

            if ( em == em2 ) {
                System.out.println("em == em2");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 엔티티 매니저 및 엔티티 매니저 팩토리 종료
            em.close();
            em2.close();
            emf.close();
        }
    }

    @Test
    @DisplayName("고쟝 이슈 테스트")
    void test12() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 엔티티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();
        try {
            //직원 엔티티 생성 및 초기화
            Employee emp = new Employee();
            emp.setName("aaa");

            //DML 작업은 반드시 트랜잭션 내에서 실행해야(해당 쿼리 실행 자체를 않음)
            tx.begin();

            em.persist(emp); //엔티티 등록됨

            emp.setName("bbb"); //관리중 엔티티에 변동 발생 -> update

            // 플러시 해주면 실행결과에 update문까지 나옴
            em.flush();

            if(em.contains(emp)){
                //emp가 컨테이너에 등록되어있음
                System.out.println("=== managed ===");
            }

            em.detach(emp);

            if(!em.contains(emp)){
                //emp가 컨테이너에 등록되어있지 않음
                System.out.println("=== detached ===");
            }
            emp.setName("ccc"); //관리 중이 아니므로 update 되지 않음

            // 관리를 해제하고 트랜잭션 커밋을 하면 'aaa' 엔티티만 등록될 것
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
    @DisplayName("update 테스트")
    void test13() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 엔티티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();
        try {
            //직원 엔티티 생성 및 초기화
            Employee emp = new Employee();

            // 직원 등록
            emp.setName("aaa");

            tx.begin();

            em.persist(emp); //엔티티 등록됨
            // UPDATE문 한번만 수행됨
            emp.setName("bbb");
            emp.setName("ccc");

            //TODO INSERT문 -> UPDATA문 수행되었음 => 파라미터 값에는 최종값만 출력이 되는건지 확인 필요

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