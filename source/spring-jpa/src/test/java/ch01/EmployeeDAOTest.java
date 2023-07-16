package ch01;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.List;


class EmployeeDAOTest {
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    @Test
    @DisplayName("회원 조회")
    void test1() {
        List<EmployeeVO> employeeList = employeeDAO.getEmployeeList();

        for (EmployeeVO vo : employeeList) {
            System.out.println("vo = " + vo.toString());
        }
    }

    @Test
    @DisplayName("회원 등록")
    void test2() {
        EmployeeVO insertVO = new EmployeeVO();
        insertVO.setName("테스트");
        insertVO.setSalary(7500.0);
        insertVO.setDeptName("부서명");
        insertVO.setTitle("과장");

        employeeDAO.setEmployee(insertVO);

        List<EmployeeVO> employeeList = employeeDAO.getEmployeeList();
        for (EmployeeVO vo : employeeList) {
            System.out.println("vo.toString() = " + vo.toString());
        }
    }
}