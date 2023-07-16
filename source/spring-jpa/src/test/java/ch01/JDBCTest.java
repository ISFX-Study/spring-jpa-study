package ch01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest {
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("JDBC 연결 테스트")
    void test1() {
        // Oracle19 버전인 경우 => "jdbc:oracle:thin:@localhost:1521:orcl"
        // Oracle11 버전인 경우 => "jdbc:oracle:thin:@localhost:1521:XE"
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "test";
        String pwd = "test";

        try (Connection conn = DriverManager.getConnection(url, user, pwd)) {
            System.out.println("접속 성공 : " + conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
