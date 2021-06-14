package com.king.service;

import com.king.model.payclass.HourlyClassification;
import com.king.model.payclass.PaymentClassification;
import com.king.model.paymethod.HoldMethod;
import com.king.model.paymethod.PaymentMethod;
import com.king.model.schedule.PaymentSchedule;
import com.king.model.schedule.WeeklySchedule;

public class AddHourlyEmployeeService extends AddEmployeeService {
    double hourSalary;

    public AddHourlyEmployeeService(int id, String name, String address, double hourSalary) {
        super(id, name, address);
        this.hourSalary = hourSalary;
    }

    @Override
    PaymentClassification getClassification() {
        return new HourlyClassification(hourSalary);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new WeeklySchedule(HourlyClassification.FRIDAY);
    }

    @Override
    PaymentMethod getMethod() {
        return new HoldMethod();
    }
}
