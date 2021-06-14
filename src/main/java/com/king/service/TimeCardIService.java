package com.king.service;

import com.king.model.*;
import com.king.model.employee.Employee;
import com.king.model.payclass.HourlyClassification;
import com.king.model.payclass.PaymentClassification;
import com.king.repository.EmployeeRepositoryImpl;

import java.time.LocalDate;

public class TimeCardIService implements IService {
    private LocalDate day;
    private double hours;
    private int employeeId;

    public TimeCardIService(LocalDate day, double hours, int employeeId) {
        this.day = day;
        this.hours = hours;
        this.employeeId = employeeId;
    }

    @Override
    public void execute() {
        Employee employee = EmployeeRepositoryImpl.getInstance().getEmployee(employeeId);
        PaymentClassification paymentClassification = employee.getPaymentClassification();
        ((HourlyClassification) paymentClassification).addTimeCard(new TimeCard(day, hours));
    }
}
