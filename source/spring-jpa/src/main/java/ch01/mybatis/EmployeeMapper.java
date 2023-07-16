package ch01.mybatis;

import ch01.EmployeeVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {
    List<EmployeeVO> selectEmpList();
}
