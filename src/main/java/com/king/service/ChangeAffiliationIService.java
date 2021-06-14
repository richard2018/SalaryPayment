package com.king.service;

import com.king.model.affilication.Affiliation;
import com.king.model.employee.Employee;

public abstract class ChangeAffiliationIService extends ChangeEmployeeIService {
    public ChangeAffiliationIService(int employeeId) {
        super(employeeId);
    }

    @Override
    void change(Employee employee) {
        recordMembership(employee);
        employee.setAffiliation(getAffiliation());
    }

    abstract Affiliation getAffiliation();

    abstract void recordMembership(Employee employee);
}
