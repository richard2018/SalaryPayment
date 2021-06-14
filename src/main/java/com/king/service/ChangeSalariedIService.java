package com.king.service;

import com.king.model.schedule.MonthlySchedule;
import com.king.model.payclass.PaymentClassification;
import com.king.model.schedule.PaymentSchedule;
import com.king.model.payclass.SalariedClassification;

public class ChangeSalariedIService extends ChangeClassificationIService {
    private double salary;

    public ChangeSalariedIService(int employeeId, double salary) {
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
