import domain.Employee07;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Ch07Test {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    @DisplayName("크라이테리어 사용")
    void test1() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", "테스트");

        try {
            /**
             * CriteriaBuilder:
             * 크라이테리어의 핵심 인터페이스, 검색 조건을 만들고 병합하는 데 사용
             * CriteriaBuilder를 사용하여 쿼리 루트를 만들고, 조건식, 연산자 및 예외 조건을 정의할 수 있음
             */
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            /**
             * CriteriaQuery:
             * JPA 크라이테리어 쿼리 만들때 사용함
             */
            CriteriaQuery<Employee07> criteriaQuery = criteriaBuilder.createQuery(Employee07.class);
            /**
             * Root:
             * 쿼리의 시작 지점
             */
            Root<Employee07> root = criteriaQuery.from(Employee07.class);

            criteriaQuery.select(root);

            // 분기 처리
            if (dataMap.containsKey("name")) {
                criteriaQuery.where(criteriaBuilder.like(root.get("name"), "%" + dataMap.get("name") + "%"));
            }

            if (dataMap.containsKey("maillId")) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("maillId"), dataMap.get("maillId")));
            }

            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

            List<Employee07> resultList = em.createQuery(criteriaQuery).getResultList();
            for (Employee07 item : resultList) {
                logger.warn("#### item = {} / {}", item.getId(), item.getName());
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
    @DisplayName("데이터 등록")
    void testInsert() {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 엔티티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();

        try {
           tx.begin();

            for (int i=0; i<=10; i++) {
                Employee07 employee07 = new Employee07();
                employee07.setName("테스트" + i);
                employee07.setTitle("테스트" + i);
                em.persist(employee07);
            }

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

