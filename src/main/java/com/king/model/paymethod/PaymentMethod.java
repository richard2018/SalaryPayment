package com.king.model.paymethod;

import com.king.model.Paycheck;

public interface PaymentMethod {
    void pay(Paycheck paycheck);
}
