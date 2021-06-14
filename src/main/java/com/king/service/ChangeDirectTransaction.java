package com.king.service;

import com.king.model.DirectMethod;
import com.king.model.PaymentMethod;

public class ChangeDirectTransaction extends ChangeMethodTransaction {
    private String account;

    public ChangeDirectTransaction(int employeeId, String account) {
        super(employeeId);
        this.account = account;
    }

    @Override
    PaymentMethod getMethod() {
        return new DirectMethod(account);
    }
}
