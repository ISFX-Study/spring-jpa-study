package ch01;

import ch01.mybatis.EmployeeMapper;
import ch01.mybatis.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MyBatisTest {
    @Test
    @DisplayName("MyBaits 연결 테스트")
    void test1() {
        EmployeeService employeeService = new EmployeeService();
        List<EmployeeVO> list = employeeService.searchEmployeeList();
        System.out.println("list = " + list);
    }
}
