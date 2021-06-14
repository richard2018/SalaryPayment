package com.king.model.affilication;

import com.king.model.Paycheck;
import com.king.model.affilication.Affiliation;

public class NoAffiliation implements Affiliation {
    @Override
    public double calculateDeductions(Paycheck paycheck) {
        return 0;
    }
}
