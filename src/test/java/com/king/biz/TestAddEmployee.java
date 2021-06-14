package com.king.biz;

import com.king.model.employee.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TestAddEmployee {
    @Autowired
    EmployeeService employeeService;

    @Test
    public void testAddHourlyEmployee() {
        Employee employee = new Employee();
        employeeService.addEmployee(employee);
    }

}
