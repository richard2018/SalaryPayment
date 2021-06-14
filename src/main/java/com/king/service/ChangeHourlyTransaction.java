package com.king.service;

import com.king.model.HourlyClassification;
import com.king.model.PaymentClassification;
import com.king.model.PaymentSchedule;
import com.king.model.WeeklySchedule;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
    private double hourlySalary;

    public ChangeHourlyTransaction(int employeeId, double hourlySalary) {
        super(employeeId);
        this.hourlySalary = hourlySalary;
    }

    @Override
    PaymentClassification getClassification() {
        return new HourlyClassification(hourlySalary);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new WeeklySchedule(HourlyClassification.FRIDAY);
    }
}
