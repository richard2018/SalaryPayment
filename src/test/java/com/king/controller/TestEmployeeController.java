package com.king.controller;

import com.king.Application;
import com.king.biz.EmployeeService;
import com.king.dto.EmployeeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestEmployeeController {

//    @MockBean
    public EmployeeService employeeService;

    @Autowired
    public EmployeeController employeeController;

    @Test
    public void test() {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(1);
        dto.setName("n1");
        dto.setAddress("a1");
        dto.setType(1);
        employeeController.addEmployee(dto);
    }
}
