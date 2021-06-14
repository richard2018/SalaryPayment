package com.king.service;

import com.king.model.payclass.CommissionedClassification;
import com.king.model.payclass.PaymentClassification;
import com.king.model.paymethod.HoldMethod;
import com.king.model.paymethod.PaymentMethod;
import com.king.model.schedule.BlweeklySchedule;
import com.king.model.schedule.PaymentSchedule;

public class AddCommissionedEmployeeService extends AddEmployeeService {
    private double salary;
    private double commissionedRate;

    public AddCommissionedEmployeeService() {
        super();
    }
    public AddCommissionedEmployeeService(int id, String name, String address, double salary, double commissionedRate) {
        super(id, name, address);
        this.salary = salary;
        this.commissionedRate = commissionedRate;
    }

    @Override
    PaymentClassification getClassification() {
        return new CommissionedClassification(salary, commissionedRate);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new BlweeklySchedule(5);
    }

    @Override
    PaymentMethod getMethod() {
        return new HoldMethod();
    }
}
