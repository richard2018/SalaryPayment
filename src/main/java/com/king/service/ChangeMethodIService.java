package com.king.service;

import com.king.model.employee.Employee;
import com.king.model.paymethod.PaymentMethod;

public abstract class ChangeMethodIService extends ChangeEmployeeIService {
    public ChangeMethodIService(int employeeId) {
        super(employeeId);
    }

    @Override
    void change(Employee employee) {
        employee.setPaymentMethod(getMethod());
    }

    abstract PaymentMethod getMethod();
}
