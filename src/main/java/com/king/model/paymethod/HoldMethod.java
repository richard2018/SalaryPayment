package com.king.model.paymethod;

import com.king.model.Paycheck;

public class HoldMethod implements PaymentMethod {
    @Override
    public void pay(Paycheck paycheck) {
        paycheck.setDisposition("Hold");
    }
}
