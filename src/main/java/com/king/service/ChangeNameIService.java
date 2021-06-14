package com.king.service;

import com.king.model.employee.Employee;

public class ChangeNameIService extends ChangeEmployeeIService {
    private String name;

    public ChangeNameIService(int employeeId, String name) {
        super(employeeId);
        this.name = name;
    }

    @Override
    void change(Employee employee) {
        employee.setName(name);
    }
}
