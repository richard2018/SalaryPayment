package com.king.service;

import com.king.model.employee.Employee;
import com.king.model.payclass.PaymentClassification;
import com.king.model.schedule.PaymentSchedule;

public abstract class ChangeClassificationIService extends ChangeEmployeeIService {

    public ChangeClassificationIService(int employeeId) {
        super(employeeId);
    }

    @Override
    void change(Employee employee) {
        employee.setPaymentClassification(getClassification());
        employee.setPaymentSchedule(getSchedule());
    }

    abstract PaymentClassification getClassification();

    abstract PaymentSchedule getSchedule();
}
