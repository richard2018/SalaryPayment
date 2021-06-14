package com.king.service;

import com.king.model.paymethod.MailMethod;
import com.king.model.paymethod.PaymentMethod;

public class ChangeMailIService extends ChangeMethodIService {
    private String mail;

    public ChangeMailIService(int employeeId, String mail) {
        super(employeeId);
        this.mail = mail;
    }

    @Override
    PaymentMethod getMethod() {
        return new MailMethod(mail);
    }
}
