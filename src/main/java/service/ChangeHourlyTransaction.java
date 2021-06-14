package service;

import model.HourlyClassification;
import model.PaymentClassification;
import model.PaymentSchedule;
import model.WeeklySchedule;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
    private double hourlySalary;

    public ChangeHourlyTransaction(int employeeId, double hourlySalary) {
        super(employeeId);
        this.hourlySalary = hourlySalary;
    }

    @Override
    PaymentClassification getClassification() {
        return new HourlyClassification(hourlySalary);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new WeeklySchedule(HourlyClassification.FRIDAY);
    }
}
