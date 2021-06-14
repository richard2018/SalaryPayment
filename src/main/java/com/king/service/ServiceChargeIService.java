package com.king.service;

import com.king.model.employee.Employee;
import com.king.model.ServiceCharge;
import com.king.model.affilication.UnionAffiliation;
import com.king.repository.EmployeeRepositoryImpl;

import java.time.LocalDate;

public class ServiceChargeIService implements IService {
    private int memberId;
    private LocalDate day;
    private double charge;

    public ServiceChargeIService(int memberId, LocalDate day, double charge) {
        this.memberId = memberId;
        this.day = day;
        this.charge = charge;
    }

    @Override
    public void execute() {
        Employee employee = EmployeeRepositoryImpl.getInstance().getUnionMember(memberId);
        if(employee.getAffiliation() instanceof UnionAffiliation){
            UnionAffiliation unionAffiliation = (UnionAffiliation) employee.getAffiliation();
            if (unionAffiliation.getMemberId() == memberId) {
                unionAffiliation.addServiceCharge(new ServiceCharge(day, charge));
            }
        }
    }
}
