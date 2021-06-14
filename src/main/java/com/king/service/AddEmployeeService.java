package com.king.service;

import com.king.model.employee.Employee;
import com.king.model.payclass.PaymentClassification;
import com.king.model.paymethod.PaymentMethod;
import com.king.model.schedule.PaymentSchedule;
import com.king.repository.EmployeeRepositoryImpl;
import com.king.service.IService;

public abstract class AddEmployeeService implements IService {
    private int id;
    private String name;
    private String address;

    public AddEmployeeService() {
    }
    public AddEmployeeService(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    abstract PaymentClassification getClassification();

    abstract PaymentSchedule getSchedule();

    abstract PaymentMethod getMethod();

    Employee generateEmployee() {
        Employee employee = new Employee(id, name, address);
        employee.setPaymentClassification(getClassification());
        employee.setPaymentSchedule(getSchedule());
        employee.setPaymentMethod(getMethod());
        return employee;
    }

    @Override
    public void execute() {
        EmployeeRepositoryImpl.getInstance().addEmployee(generateEmployee());
    }
}
