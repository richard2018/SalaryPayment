package com.king.service;

import com.king.model.Affiliation;
import com.king.model.Employee;
import com.king.model.NoAffiliation;
import com.king.model.UnionAffiliation;
import com.king.repository.EmployeeRepositoryImpl;

public class ChangeUnffiliatedTransaction extends ChangeAffiliationTransaction {
    public ChangeUnffiliatedTransaction(int employeeId) {
        super(employeeId);
    }

    @Override
    Affiliation getAffiliation() {
        return new NoAffiliation();
    }

    @Override
    void recordMembership(Employee employee) {
        Affiliation affiliation = employee.getAffiliation();
        if(affiliation instanceof UnionAffiliation) {
            UnionAffiliation unionAffiliation = (UnionAffiliation) affiliation;
            EmployeeRepositoryImpl.getInstance().removeUnionMember(unionAffiliation.getMemberId());
        }
    }
}
