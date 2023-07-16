package ch01.mybatis;

import ch01.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper mapper;

    public List<EmployeeVO> searchEmployeeList() {
        List<EmployeeVO> list = mapper.selectEmpList();
        return list;
    }
}
