package com.king.service;

import com.king.model.Affiliation;
import com.king.model.Employee;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {
    public ChangeAffiliationTransaction(int employeeId) {
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
