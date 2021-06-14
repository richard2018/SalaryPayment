package com.king.service;

import com.king.model.employee.Employee;
import com.king.repository.EmployeeRepositoryImpl;

public abstract class ChangeEmployeeIService implements IService {
    int employeeId;

    public ChangeEmployeeIService(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public void execute() {
        Employee employee = EmployeeRepositoryImpl.getInstance().getEmployee(employeeId);
        if (employee != null) {
            change(employee);
        }
    }

    abstract void change(Employee employee);
}
