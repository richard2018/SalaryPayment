package com.king.service;

import com.king.model.Employee;
import com.king.repository.EmployeeRepositoryImpl;
import com.king.model.Transaction;

public abstract class ChangeEmployeeTransaction implements Transaction {
    int employeeId;

    public ChangeEmployeeTransaction(int employeeId) {
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
