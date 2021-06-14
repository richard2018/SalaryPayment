package com.king.service;

import com.king.model.schedule.BlweeklySchedule;
import com.king.model.payclass.CommissionedClassification;
import com.king.model.payclass.PaymentClassification;
import com.king.model.schedule.PaymentSchedule;

public class ChangeCommissionedClassification extends ChangeClassificationIService {
    private double salary;
    private double commissionRate;

    public ChangeCommissionedClassification(int employeeId, double salary, double commissionRate) {
        super(employeeId);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    @Override
    PaymentClassification getClassification() {
        return new CommissionedClassification(salary, commissionRate);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new BlweeklySchedule(5);
    }
}
