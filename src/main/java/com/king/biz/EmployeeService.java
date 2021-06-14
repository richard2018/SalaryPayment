package com.king.biz;

import com.king.data.EmployeeDO;
import com.king.mapper.EmployeeMapper;
import com.king.model.employee.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }
    public void addEmployee(Employee employee) {
        EmployeeDO employeeDO = new EmployeeDO();
        BeanUtils.copyProperties(employee, employeeDO);
        employeeMapper.insert(employeeDO);
    }
//    @Autowired
    private EmployeeMapper employeeMapper;
}
