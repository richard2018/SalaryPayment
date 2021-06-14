package com.king.service;

import com.king.model.paymethod.HoldMethod;
import com.king.model.paymethod.PaymentMethod;

public class ChangeHoldIService extends ChangeMethodIService {
    public ChangeHoldIService(int employeeId) {
        super(employeeId);
    }

    @Override
    PaymentMethod getMethod() {
        return new HoldMethod();
    }
}
