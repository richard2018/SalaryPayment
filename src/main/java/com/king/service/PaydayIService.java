package com.king.service;

import com.king.model.employee.Employee;
import com.king.repository.EmployeeRepositoryImpl;
import com.king.model.Paycheck;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaydayIService implements IService {
    private LocalDate date;
    Map<Integer, Paycheck> paycheckMap = new HashMap<>();

    public PaydayIService(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute() {
        List<Integer> employeeIds = EmployeeRepositoryImpl.getInstance().getAllEmployeeIds();
        for (Integer employeeId : employeeIds) {
            Employee employee = EmployeeRepositoryImpl.getInstance().getEmployee(employeeId);
            if (employee.isPayDate(date)) {
                Paycheck paycheck = new Paycheck(employee.getPayPeriodStartDate(date), date);
                paycheckMap.put(employeeId, paycheck);
                employee.payday(paycheck);
            }
        }
    }

    public Paycheck getPaycheck(int employeeId) {
        return paycheckMap.get(employeeId);
    }
}
