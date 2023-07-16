package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";    // DB URL
    private final String DB_USER = "test";    // 계정
    private final String DB_PWD = "test";     // 비밀번호

    // JDBC APIs
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    /**
     * 회원등록
     * @param vo
     */
    public void setEmployee(EmployeeVO vo) {
        try {
            // 1. dirver연결
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. 계정 연결
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);

            // 3. 쿼리문 작성
            StringBuffer sb = new StringBuffer();
            sb.append("INSERT INTO S_EMP");
            sb.append("    (");
            sb.append("        ID");
            sb.append("      , NAME");
            sb.append("      , START_DATE");
            sb.append("      , TITLE");
            sb.append("      , DEPT_NAME");
            sb.append("      , SALARY");
            sb.append("    )");
            sb.append("VALUES");
            sb.append("    (");
            sb.append("       ( SELECT NVL(MAX(ID), 0) + 1 FROM S_EMP )");
            sb.append("      , ?");
            sb.append("      , SYSDATE");
            sb.append("      , ?");
            sb.append("      , ?");
            sb.append("      , ?");
            sb.append("    )");

            // 4. 쿼리 실행
            stmt = conn.prepareStatement(sb.toString());
            stmt.setString(1, vo.getName());
            stmt.setString(2, vo.getTitle());
            stmt.setString(3, vo.getDeptName());
            stmt.setDouble(4, vo.getSalary());

            // 5. 실행 결과 확인
            rs = stmt.executeQuery();

            // 6. 연결 종료
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     /**
     * 회원 조회
     * @param vo
     */
    public List<EmployeeVO> getEmployeeList() {
        List<EmployeeVO> employeeList = new ArrayList<>();

        try {
            // 1. dirver연결
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. 계정 연결
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);

            // 3. 쿼리문 작성
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT  ID");
            sb.append("      , NAME");
            sb.append("      , START_DATE");
            sb.append("      , TITLE");
            sb.append("      , DEPT_NAME");
            sb.append("      , SALARY");
            sb.append("  FROM S_EMP");

            // 4. 쿼리 실행
            stmt = conn.prepareStatement(sb.toString());

            // 5. 실행 결과 확인
            rs = stmt.executeQuery();

            while ( rs.next() ) {
                EmployeeVO employee = new EmployeeVO();
                employee.setId(rs.getLong("ID"));
                employee.setName(rs.getString("NAME"));
                employee.setStartDate(rs.getTimestamp("START_DATE"));
                employee.setTitle(rs.getString("TITLE"));
                employee.setDeptName(rs.getString("DEPT_NAME"));
                employee.setSalary(rs.getDouble("SALARY"));

                employeeList.add(employee);
            }

            // 6. 연결 종료
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employeeList;
    }
}