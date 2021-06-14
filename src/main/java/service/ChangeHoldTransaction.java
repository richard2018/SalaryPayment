package service;

import model.HoldMethod;
import model.PaymentMethod;

public class ChangeHoldTransaction extends ChangeMethodTransaction {
    public ChangeHoldTransaction(int employeeId) {
        super(employeeId);
    }

    @Override
    PaymentMethod getMethod() {
        return new HoldMethod();
    }
}
