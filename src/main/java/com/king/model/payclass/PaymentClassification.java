package com.king.model.payclass;

import com.king.model.Paycheck;

public interface PaymentClassification {
    double calculatePay(Paycheck paycheck);
}
