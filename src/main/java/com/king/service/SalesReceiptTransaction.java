package com.king.service;

import com.king.model.Employee;
import com.king.model.PaymentClassification;
import com.king.model.SalesReceipt;
import com.king.model.Transaction;
import com.king.model.*;
import com.king.repository.EmployeeRepositoryImpl;

import java.time.LocalDate;

public class SalesReceiptTransaction implements Transaction {
    private LocalDate day;
    private int amount;
    private int employeeId;

    public SalesReceiptTransaction(LocalDate day, int amount, int employeeId) {
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
