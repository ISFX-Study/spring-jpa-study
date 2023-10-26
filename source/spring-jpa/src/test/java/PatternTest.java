import com.study.springjpa.domain.EmployeeBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PatternTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    @DisplayName("Builder Pattern")
    void test1() {
        EmployeeBuilder builder1 = new EmployeeBuilder(Long.valueOf("1"), "생성자", "사원", "20231001");
        logger.info("### 생성자 이용 => {}", builder1.toString());

        EmployeeBuilder builder2 = new EmployeeBuilder();
        builder2.id(Long.valueOf("2"))
                .name("빌더")
                .title("과장")
                .startDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        logger.info("### Builder 패턴 이용 => {}", builder2.toString());
    }
}
