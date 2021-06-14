package com.king.controller;

import com.king.biz.EmployeeService;
import com.king.model.employee.Employee;
import com.king.dto.EmployeeDto;
import com.king.res.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping("/addEmployee")
    public Response<EmployeeDto> addEmployee(@RequestBody EmployeeDto dto) {
        System.out.println(this.getClass() + " addEmployee...");
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);
        Response<EmployeeDto> resResponse = new Response<>();
        employeeService.addEmployee(employee);
        return resResponse;
    }

//    @Autowired
    private EmployeeService employeeService;
}
