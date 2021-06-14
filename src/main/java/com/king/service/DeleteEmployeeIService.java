package com.king.service;

import com.king.repository.EmployeeRepositoryImpl;

public class DeleteEmployeeIService implements IService {
    private int employeeId;

    public DeleteEmployeeIService(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public void execute() {
        EmployeeRepositoryImpl.getInstance().delete(employeeId);
    }
}
