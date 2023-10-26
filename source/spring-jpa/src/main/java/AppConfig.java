import com.study.springjpa.repository.EmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public EmployeeRepository employeeRepository() {
        System.out.println("EmployeeRepository() 호출");
        return new EmployeeRepository();
    }
}
