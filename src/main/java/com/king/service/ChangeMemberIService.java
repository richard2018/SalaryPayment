package com.king.service;

import com.king.model.affilication.Affiliation;
import com.king.model.employee.Employee;
import com.king.repository.EmployeeRepositoryImpl;
import com.king.model.affilication.UnionAffiliation;

public class ChangeMemberIService extends ChangeAffiliationIService {
    private int memberId;
    private double charge;

    public ChangeMemberIService(int employeeId, int memberId, double charge) {
        super(employeeId);
        this.memberId = memberId;
        this.charge = charge;
    }

    @Override
    Affiliation getAffiliation() {
        return new UnionAffiliation(memberId, charge);
    }

    @Override
    void recordMembership(Employee employee) {
        EmployeeRepositoryImpl.getInstance().addUnionMember(memberId, employee);
    }
}
