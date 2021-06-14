package com.king.service;

import com.king.model.payclass.CommissionedClassification;
import com.king.model.employee.Employee;
import com.king.model.payclass.PaymentClassification;
import com.king.model.SalesReceipt;
import com.king.repository.EmployeeRepositoryImpl;

import java.time.LocalDate;

public class SalesReceiptIService implements IService {
    private LocalDate day;
    private int amount;
    private int employeeId;

    public SalesReceiptIService(LocalDate day, int amount, int employeeId) {
        this.day = day;
        this.amount = amount;
        this.employeeId = employeeId;
    }

    @Override
    public void execute() {
        Employee employee = EmployeeRepositoryImpl.getInstance().getEmployee(employeeId);
        PaymentClassification paymentClassification = employee.getPaymentClassification();
        ((CommissionedClassification)paymentClassification).addSalesReceipt(new SalesReceipt(day,amount));
    }
}
