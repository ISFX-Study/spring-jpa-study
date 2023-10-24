import domain.Employee;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repository.EmployeeJpaRepository;

import java.util.Collections;
import java.util.List;

/*
 * @DataJpaTest :  @Entiy와 스프링 데이터 JPA 레파지토리를 설정함
 */
//@DataJpaTest
@SpringBootTest(classes = Ch08Test.class)
class Ch08Test {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private EmployeeJpaRepository employeeJpaRepository;

    private Employee employee, employee2;

    /**
     * 테스트 메서드 이전에 수행함
     */
    @BeforeEach
    void loadData() {
        employee = new Employee();
        employee.setName("테스트");

        employee2 = new Employee();
        employee2.setName("테스트2");

        employeeJpaRepository.saveAll(List.of(employee, employee2));
    }

    @Test
    @DisplayName("스프링 데이터 JPA 사용")
    void test1() {
        Assertions.assertEquals(List.of(employee, employee2), employeeJpaRepository.findAll());
    }
}
