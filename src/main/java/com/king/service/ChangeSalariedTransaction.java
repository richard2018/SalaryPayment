package com.king.service;

import com.king.model.MonthlySchedule;
import com.king.model.PaymentClassification;
import com.king.model.PaymentSchedule;
import com.king.model.SalariedClassification;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction {
    private double salary;

    public ChangeSalariedTransaction(int employeeId, double salary) {
        super(employeeId);
        this.salary = salary;
    }

    @Override
    PaymentClassification getClassification() {
        return new SalariedClassification(salary);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }
}
