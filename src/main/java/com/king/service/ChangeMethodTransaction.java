package com.king.service;

import com.king.model.Employee;
import com.king.model.PaymentMethod;

public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {
    public ChangeMethodTransaction(int employeeId) {
        super(employeeId);
    }

    @Override
    void change(Employee employee) {
        employee.setPaymentMethod(getMethod());
    }

    abstract PaymentMethod getMethod();
}
