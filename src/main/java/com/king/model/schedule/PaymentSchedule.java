package com.king.model.schedule;

import java.time.LocalDate;

public interface PaymentSchedule {
    boolean isPayDay(LocalDate date);

    LocalDate getPayPeriodStartDate(LocalDate payDate);
}
