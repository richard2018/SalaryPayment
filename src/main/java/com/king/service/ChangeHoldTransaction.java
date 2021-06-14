package com.king.service;

import com.king.model.HoldMethod;
import com.king.model.PaymentMethod;

public class ChangeHoldTransaction extends ChangeMethodTransaction {
    public ChangeHoldTransaction(int employeeId) {
        super(employeeId);
    }

    @Override
    PaymentMethod getMethod() {
        return new HoldMethod();
    }
}
