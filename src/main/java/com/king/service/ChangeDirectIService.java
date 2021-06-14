package com.king.service;

import com.king.model.paymethod.DirectMethod;
import com.king.model.paymethod.PaymentMethod;

public class ChangeDirectIService extends ChangeMethodIService {
    private String account;

    public ChangeDirectIService(int employeeId, String account) {
        super(employeeId);
        this.account = account;
    }

    @Override
    PaymentMethod getMethod() {
        return new DirectMethod(account);
    }
}
