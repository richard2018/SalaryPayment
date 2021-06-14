package com.king.service;

import com.king.model.payclass.HourlyClassification;
import com.king.model.payclass.PaymentClassification;
import com.king.model.schedule.PaymentSchedule;
import com.king.model.schedule.WeeklySchedule;

public class ChangeHourlyIService extends ChangeClassificationIService {
    private double hourlySalary;

    public ChangeHourlyIService(int employeeId, double hourlySalary) {
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
