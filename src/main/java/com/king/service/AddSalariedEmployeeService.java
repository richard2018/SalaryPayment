package com.king.service;

import com.king.model.payclass.PaymentClassification;
import com.king.model.payclass.SalariedClassification;
import com.king.model.paymethod.HoldMethod;
import com.king.model.paymethod.PaymentMethod;
import com.king.model.schedule.MonthlySchedule;
import com.king.model.schedule.PaymentSchedule;

public class AddSalariedEmployeeService extends AddEmployeeService {
    private double salary;

    public AddSalariedEmployeeService(int id, String name, String address, double salary) {
        super(id, name, address);
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

    @Override
    PaymentMethod getMethod() {
        return new HoldMethod();
    }
}
