package service;

import model.MonthlySchedule;
import model.PaymentClassification;
import model.PaymentSchedule;
import model.SalariedClassification;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction {
    private double salary;

    public ChangeSalariedTransaction(int employeeId, double salary) {
        super(employeeId);
        this.salary = salary;
    }

    @Override
    PaymentClassification getClassification() {
        return new SalariedClassification(salary);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }
}
