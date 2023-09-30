import domain.Employee;
import domain.EmployeeCardCh05;
import domain.EmployeeCh05;
import lombok.extern.java.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

public class Ch06Test {
    @Test
    @DisplayName("JPQL 조회")
    void test1() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        StringBuffer sb = new StringBuffer();
//        sb.append("SELECT e ");
//        sb.append("  FROM Employee AS e");

//        sb.append("SELECT e ");
        // SELECT절 생략가능
        sb.append("  FROM Employee e");

        // 에러남
//        sb.append("SELECT *");
//        sb.append("  FROM Employee");

        StringBuffer sb2 = new StringBuffer();
        sb2.append("SELECT id");
        sb2.append("     , name");
        sb2.append("  FROM Employee");

        try {
            // 전체 컬럼 가져오는 경우
            TypedQuery<Employee> query = em.createQuery(sb.toString(), Employee.class);
            List<Employee> list = query.getResultList();

            for (Employee employee: list) {
                System.out.println("## employee : " + employee);
            }

            // 특정 컬럼만 가져오는 경우
            Query query2 = em.createQuery(sb2.toString());
            List<Object[]> list2 = query2.getResultList();

            for (Object[] employee: list2) {
                System.out.println("## employee : " + Arrays.toString(employee));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 엔티티 매니저 및 엔티티 매니저 팩토리 종료
            em.close();
            emf.close();
        }
    }

    @Test
    @DisplayName("JPQL 조회 - 조회조건 있는경우")
    void test2() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT id");
        sb.append("     , name");
        sb.append("  FROM Employee");
        sb.append(" WHERE id = ?1");
        sb.append("   AND name = ?2");

        StringBuffer sb2 = new StringBuffer();
        sb2.append("SELECT id");
        sb2.append("     , name");
        sb2.append("  FROM Employee");
        sb2.append(" WHERE id = :id");
        sb2.append("   AND name = :name");

        try {
            // 파라미터 바인딩 방법1)
            Query query = em.createQuery(sb.toString());
            query.setParameter(1, Long.valueOf("2"));
            query.setParameter(2, "개발자0");

            Object[] result = (Object[]) query.getSingleResult();
            System.out.println("### 파라미터 바인딩 방법1) result = " +  Arrays.toString(result));

            // 파라미터 바인딩 방법2)
            Query query2 = em.createQuery(sb2.toString());
            query2.setParameter("id", Long.valueOf("2"));
            query2.setParameter("name", "개발자0");

            // TODO getSingleResult() 반환값은 Object인데 Object[] 받아야 제대로 출력할 수 있음
            Object[] result2 = (Object[]) query2.getSingleResult();
            for (Object item : result2) {
                System.out.println("### 파라미터 바인딩 방법2) item = " +  item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 엔티티 매니저 및 엔티티 매니저 팩토리 종료
            em.close();
            emf.close();
        }
    }
    @Test
    @DisplayName("find vs JPQL")
    void test3() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT id");
        sb.append("     , name");
        sb.append("  FROM Employee");
        sb.append(" WHERE id = :id");
        sb.append("   AND name = :name");


        try {
            Employee find = em.find(Employee.class, Long.valueOf("2"));
            Employee find2 = em.find(Employee.class, Long.valueOf("2"));
            System.out.println("## find = " + find);
            System.out.println("## find2 = " + find2);

            if ( find == find2 ) {
                System.out.println("##### 같다 #####");
            }

            Query query = em.createQuery(sb.toString());
            query.setParameter("id", Long.valueOf("2"));
            query.setParameter("name", "개발자0");

            Object[] result = (Object[]) query.getSingleResult();
            Object[] result2 = (Object[]) query.getSingleResult();
            System.out.println("## result = " + result);
            System.out.println("## result2 = " + result2);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 엔티티 매니저 및 엔티티 매니저 팩토리 종료
            em.close();
            emf.close();
        }
    }

    @Test
    @DisplayName("조인")
    void test4() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        StringBuffer innerSb = new StringBuffer();
        innerSb.append("SELECT e, d");
        innerSb.append("  FROM Employee e");
        innerSb.append(" INNER JOIN e.dept d");

        StringBuffer outerSb = new StringBuffer();
        outerSb.append("SELECT e, d");
        outerSb.append("  FROM Employee e");
        outerSb.append("  LEFT OUTER JOIN e.dept d");
        try {
            TypedQuery<Object[]> query = em.createQuery(innerSb.toString(), Object[].class);
            TypedQuery<Object[]> query2 = em.createQuery(outerSb.toString(), Object[].class);

            List<Object[]> list = query.getResultList();
            System.out.println("############ INNER JOIN ############");
            for (Object[] item : list) {
                System.out.println("### item = " + Arrays.toString(item));
            }

            List<Object[]> list2 = query2.getResultList();
            System.out.println("############ OUTER JOIN ############");
            for (Object[] item : list2) {
                System.out.println("### item = " + Arrays.toString(item));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 엔티티 매니저 및 엔티티 매니저 팩토리 종료
            em.close();
            emf.close();
        }
    }

    @Test
    @DisplayName("다양한 쿼리 사용하기")
    void test5() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        try {
            // @NamedQuery 이용
            List<Employee> list = em.createNamedQuery("Employee.searchNamedQuery", Employee.class)
                    .setParameter("name", "%개발자%")
                    .getResultList();
            System.out.println("### @NamedQuery  : " + list);

            // XML - NamedQuery 이용
            List<Employee> list2 = em.createNamedQuery("Employee.searchNamedQuery2", Employee.class)
                         .setParameter("name", "%개발자%")
                         .getResultList();
            System.out.println("### createQuery : " + list2);

            // XML - NamedNativeQuery 이용
            Query query2 = em.createNamedQuery("Employee.searchNativeQuery2");
            query2.setParameter("name", "개발자");
            List<Object[]> list4 = query2.getResultList();
            for (Object[] item : list4) {
                System.out.println("### item = " + Arrays.toString(item));
            }

            // TODO 결과 타입 오류 발생
//            TypedQuery<Employee> query = em.createNamedQuery("Employee.searchNativeQuery", Employee.class);
//            query.setParameter("name", "개발자");
//            List<Employee> list3 = query.getResultList();
//            System.out.println("### @NamedNativeQuery : " + list3.size());

            // @NamedNativeQuery 이용
            Query query = em.createNamedQuery("Employee.searchNativeQuery");
            query.setParameter("name", "개발자");
            List<Object[]> list3 = query.getResultList();
            for (Object[] item : list3) {
                System.out.println("### item = " + Arrays.toString(item));
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
