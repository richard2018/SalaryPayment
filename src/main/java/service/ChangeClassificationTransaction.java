package service;

import model.Employee;
import model.PaymentClassification;
import model.PaymentSchedule;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {

    public ChangeClassificationTransaction(int employeeId) {
        super(employeeId);
    }

    @Override
    void change(Employee employee) {
        employee.setPaymentClassification(getClassification());
        employee.setPaymentSchedule(getSchedule());
    }

    abstract PaymentClassification getClassification();

    abstract PaymentSchedule getSchedule();
}
