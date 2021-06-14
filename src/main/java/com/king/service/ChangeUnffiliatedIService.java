package com.king.service;

import com.king.model.affilication.Affiliation;
import com.king.model.employee.Employee;
import com.king.model.affilication.NoAffiliation;
import com.king.model.affilication.UnionAffiliation;
import com.king.repository.EmployeeRepositoryImpl;

public class ChangeUnffiliatedIService extends ChangeAffiliationIService {
    public ChangeUnffiliatedIService(int employeeId) {
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
