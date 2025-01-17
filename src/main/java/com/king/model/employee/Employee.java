package com.king.model.employee;

import com.king.model.affilication.NoAffiliation;
import com.king.model.Paycheck;
import com.king.model.payclass.PaymentClassification;
import com.king.model.paymethod.PaymentMethod;
import com.king.model.affilication.Affiliation;
import com.king.model.schedule.PaymentSchedule;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String name;
    private String address;
    private int type;
    private PaymentClassification paymentClassification;
    private PaymentSchedule paymentSchedule;
    private PaymentMethod paymentMethod;

    private Affiliation affiliation = new NoAffiliation();

    public Employee() {
    }
    public Employee(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public PaymentClassification getPaymentClassification() {
        return paymentClassification;
    }

    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentClassification(PaymentClassification paymentClassification) {
        this.paymentClassification = paymentClassification;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPayDate(LocalDate date) {
        return getPaymentSchedule().isPayDay(date);
    }

    public void payday(Paycheck paycheck) {
        double grossPay = getPaymentClassification().calculatePay(paycheck);
        double deductions = getAffiliation().calculateDeductions(paycheck);
        double netPay = grossPay - deductions;
        paycheck.setGrossPay(grossPay);
        paycheck.setDeductions(deductions);
        paycheck.setNetPay(netPay);
        getPaymentMethod().pay(paycheck);
    }

    public LocalDate getPayPeriodStartDate(LocalDate payDate) {
        return getPaymentSchedule().getPayPeriodStartDate(payDate);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
