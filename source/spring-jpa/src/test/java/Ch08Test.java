import domain.Employee;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
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


@SpringBootTest
@ExtendWith(SpringExtension.class)
class Ch08Test {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

    @Test
    @DisplayName("스프링 데이터 JPA 사용")
    void test1() {
        Employee employee = new Employee();
        employee.setName("스프링데이터JPA");

        Employee saveEntiy = employeeJpaRepository.save(employee);

        Assertions.assertEquals("스프링데이터JPA", saveEntiy.getName());
    }
}
