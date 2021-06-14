import com.king.model.affilication.Affiliation;
import com.king.model.affilication.NoAffiliation;
import com.king.model.affilication.UnionAffiliation;
import com.king.service.AddCommissionedEmployeeService;
import com.king.service.AddHourlyEmployeeService;
import com.king.service.AddSalariedEmployeeService;
import com.king.model.employee.Employee;
import com.king.model.payclass.CommissionedClassification;
import com.king.model.payclass.HourlyClassification;
import com.king.model.payclass.PaymentClassification;
import com.king.model.payclass.SalariedClassification;
import com.king.model.paymethod.MailMethod;
import com.king.model.schedule.BlweeklySchedule;
import com.king.model.schedule.MonthlySchedule;
import com.king.model.schedule.WeeklySchedule;
import com.king.service.*;;
import com.king.model.*;
import org.junit.Assert;
import org.junit.Test;
import com.king.repository.EmployeeRepository;
import com.king.repository.EmployeeRepositoryImpl;

import java.time.LocalDate;

public class TestAddSalariedEmployee {
    private EmployeeRepository employeeRepository = EmployeeRepositoryImpl.getInstance();

    /**
     * 增加钟点工
     */
    @Test
    public void should_add_hourly_employee() {
        //give
        int employeeId = 1;
        String name = "Bob";
        String address = "Home";
        double hourSalary = 50.00;
        AddHourlyEmployeeService addHourlyEmployee = new AddHourlyEmployeeService(employeeId, name, address, hourSalary);
        //when
        addHourlyEmployee.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        Assert.assertEquals(employeeId, employee.getId());
        Assert.assertEquals(name, employee.getName());
        Assert.assertEquals(address, employee.getAddress());
        Assert.assertTrue(employee.getPaymentClassification() instanceof HourlyClassification);
        Assert.assertEquals(hourSalary, ((HourlyClassification) employee.getPaymentClassification()).getHourlySalary(), 0.01);
        Assert.assertTrue(employee.getPaymentSchedule() instanceof WeeklySchedule);
        Assert.assertEquals(5, ((WeeklySchedule) employee.getPaymentSchedule()).getValue());
    }

    /**
     * 增加带薪雇员
     */
    @Test
    public void should_add_salaried_employee() {
        //give
        int employeeId = 1;
        String name = "name";
        String address = "address";
        double salary = 1000.00;
        AddSalariedEmployeeService addSalariedEmployee = new AddSalariedEmployeeService(employeeId, name, address, salary);
        //when
        addSalariedEmployee.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        Assert.assertEquals(employeeId, employee.getId());
        Assert.assertEquals(name, employee.getName());
        Assert.assertEquals(address, employee.getAddress());
        Assert.assertTrue(employee.getPaymentClassification() instanceof SalariedClassification);
        Assert.assertEquals(salary, ((SalariedClassification) employee.getPaymentClassification()).getSalary(), 0.01);
        Assert.assertTrue(employee.getPaymentSchedule() instanceof MonthlySchedule);
    }

    /**
     * 增加带薪销售雇员
     */
    @Test
    public void should_add_commissioned_employee() {
        //give
        int employeeId = 1;
        String name = "John";
        String address = "Street A";
        double salary = 500.00;
        double commissionRate = 100.00;
        AddCommissionedEmployeeService addCommissionedEmployee = new AddCommissionedEmployeeService(employeeId, name, address, salary, commissionRate);
        //when
        addCommissionedEmployee.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        Assert.assertEquals(employeeId, employee.getId());
        Assert.assertEquals(name, employee.getName());
        Assert.assertEquals(address, employee.getAddress());
        Assert.assertTrue(employee.getPaymentClassification() instanceof CommissionedClassification);
        Assert.assertEquals(salary, ((CommissionedClassification) employee.getPaymentClassification()).getSalary(), 0.01);
        Assert.assertEquals(commissionRate, ((CommissionedClassification) employee.getPaymentClassification()).getCommissionRate(), 0.01);
        Assert.assertTrue(employee.getPaymentSchedule() instanceof BlweeklySchedule);
        Assert.assertEquals(5, ((BlweeklySchedule) employee.getPaymentSchedule()).getValue());
    }

    /**
     * 删除雇员
     */
    @Test
    public void should_delete_employee() {
        //give
        int employeeId = 3;
        AddCommissionedEmployeeService addCommissionedEmployee = new AddCommissionedEmployeeService(employeeId, "name", "address", 100.00, 10.50);
        addCommissionedEmployee.execute();
        //when
        {
            Employee employee = employeeRepository.getEmployee(employeeId);
            Assert.assertNotNull(employee);
        }
        DeleteEmployeeIService deleteEmployeeTransaction = new DeleteEmployeeIService(employeeId);
        deleteEmployeeTransaction.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNull(employee);
    }

    /**
     * 登记时间卡
     */
    @Test
    public void should_add_time_card() {
        //give
        int employeeId = 1;
        new AddHourlyEmployeeService(employeeId, "name", "address", 50.00).execute();
        LocalDate day = LocalDate.of(2017, 11, 20);
        //when
        TimeCardIService timeCardTransaction = new TimeCardIService(day, 8.0, employeeId);
        timeCardTransaction.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        PaymentClassification paymentClassification = employee.getPaymentClassification();
        Assert.assertTrue(paymentClassification instanceof HourlyClassification);
        TimeCard timeCard = ((HourlyClassification) paymentClassification).getTimeCard(day);
        Assert.assertNotNull(timeCard);
        Assert.assertEquals(8.0, timeCard.getHours(), 0.01);
    }

    /**
     * 登记销售凭条
     */
    @Test
    public void should_add_sales_receipt() {
        //give
        int employeeId = 1;
        new AddCommissionedEmployeeService(employeeId, "name", "address", 1000.00, 100.50).execute();
        LocalDate day = LocalDate.of(2017, 11, 20);
        //when
        SalesReceiptIService salesReceiptTransaction = new SalesReceiptIService(day, 5, employeeId);
        salesReceiptTransaction.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        PaymentClassification paymentClassification = employee.getPaymentClassification();
        Assert.assertTrue(paymentClassification instanceof CommissionedClassification);
        SalesReceipt salesReceipt = ((CommissionedClassification) paymentClassification).getSalesReceipt(day);
        Assert.assertNotNull(salesReceipt);
        Assert.assertEquals(5, salesReceipt.getAmount());
    }

    /**
     * 登记服务费用
     */
    @Test
    public void should_add_service_charge() {
        //give
        int employeeId = 2;
        int memberId = 10;
        new AddHourlyEmployeeService(employeeId, "name", "address", 50.3).execute();
        Employee employee = employeeRepository.getEmployee(employeeId);
        //when
        UnionAffiliation unionAffiliation = new UnionAffiliation(memberId, 12.5);
        employee.setAffiliation(unionAffiliation);
        employeeRepository.addUnionMember(memberId, employee);
        LocalDate day = LocalDate.of(2017, 11, 1);
        ServiceChargeIService serviceChargeTransaction = new ServiceChargeIService(memberId, day, 12.95);
        serviceChargeTransaction.execute();
        //then
        Assert.assertNotNull(employee);
        ServiceCharge serviceCharge = unionAffiliation.getServiceCharge(day);
        Assert.assertNotNull(serviceCharge);
        Assert.assertEquals(12.95, serviceCharge.getAmount(), 0.01);
    }

    /**
     * 修改雇员属性name
     */
    @Test
    public void should_change_employee_name() {
        //give
        int employeeId = 4;
        AddHourlyEmployeeService addHourlyEmployee = new AddHourlyEmployeeService(employeeId, "Jake", "House", 60.0);
        addHourlyEmployee.execute();
        //when
        ChangeNameIService changeNameTransaction = new ChangeNameIService(employeeId, "Frank");
        changeNameTransaction.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        Assert.assertEquals("Frank", employee.getName());
    }

    /**
     * 修改雇员类别
     */
    @Test
    public void should_change_employee_classification() {
        //give
        int employeeId = 3;
        AddCommissionedEmployeeService addCommissionedEmployee = new AddCommissionedEmployeeService(employeeId, "name", "address", 1000.00, 200.00);
        addCommissionedEmployee.execute();
        //when
        ChangeHourlyIService changeHourlyTransaction = new ChangeHourlyIService(employeeId, 80.73);
        changeHourlyTransaction.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        Assert.assertEquals(employeeId, employee.getId());
        Assert.assertTrue(employee.getPaymentClassification() instanceof HourlyClassification);
        Assert.assertEquals(80.73, ((HourlyClassification) employee.getPaymentClassification()).getHourlySalary(), 0.01);
        Assert.assertTrue(employee.getPaymentSchedule() instanceof WeeklySchedule);
        Assert.assertEquals(5, ((WeeklySchedule) employee.getPaymentSchedule()).getValue());
    }

    /**
     * 修改雇员支付方式
     */
    @Test
    public void should_change_employee_payment_method() {
        //give
        int employeeId = 1;
        AddHourlyEmployeeService addHourlyEmployee = new AddHourlyEmployeeService(employeeId, "name", "address", 50.34);
        addHourlyEmployee.execute();
        //when
        String mail = "mail@mail.com";
        ChangeMailIService changeMailTransaction = new ChangeMailIService(employeeId, mail);
        changeMailTransaction.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        Assert.assertTrue(employee.getPaymentMethod() instanceof MailMethod);
        Assert.assertEquals(mail, ((MailMethod) employee.getPaymentMethod()).getMailAddress());
    }

    /**
     * 修改雇员协会属性
     */
    @Test
    public void should_change_employee_affiliation() {
        //give
        int employeeId = 2;
        int memberId = 47;
        AddHourlyEmployeeService addHourlyEmployee = new AddHourlyEmployeeService(employeeId, "name", "address", 35.00);
        addHourlyEmployee.execute();
        //when
        ChangeMemberIService changeMemberTransaction = new ChangeMemberIService(employeeId, memberId, 39.00);
        changeMemberTransaction.execute();
        //then
        Employee employee = employeeRepository.getEmployee(employeeId);
        Assert.assertNotNull(employee);
        Affiliation affiliation = employee.getAffiliation();
        Assert.assertNotNull(affiliation);
        Assert.assertTrue(affiliation instanceof UnionAffiliation);
        UnionAffiliation unionAffiliation = (UnionAffiliation) affiliation;
        Assert.assertEquals(39.00, unionAffiliation.getWeeklyCharge(), 0.01);
        Employee member = employeeRepository.getUnionMember(memberId);
        Assert.assertNotNull(member);
        Assert.assertSame(employee, member);
    }

    /**
     * 删除协会成员
     */
    @Test
    public void should_remove_union_member() {
        //give
        int employeeId = 2;
        int memberId = 47;
        AddHourlyEmployeeService addHourlyEmployee = new AddHourlyEmployeeService(employeeId, "name", "address", 35.00);
        addHourlyEmployee.execute();
        Employee employee = employeeRepository.getEmployee(employeeId);
        UnionAffiliation unionAffiliation = new UnionAffiliation(memberId, employeeId);
        employee.setAffiliation(unionAffiliation);
        employeeRepository.addUnionMember(memberId, employee);
        //when
        ChangeUnffiliatedIService changeUnffiliatedTransaction = new ChangeUnffiliatedIService(employeeId);
        changeUnffiliatedTransaction.execute();
        //then
        Assert.assertTrue(employee.getAffiliation() instanceof NoAffiliation);
        Assert.assertNull(employeeRepository.getUnionMember(memberId));
    }

    /**
     * 支付带薪雇员薪水
     */
    @Test
    public void should_pay_single_salaried_employee() {
        //give
        int employeeId = 1;
        AddSalariedEmployeeService addSalariedEmployee = new AddSalariedEmployeeService(employeeId, "Name", "Address", 1000.00);
        addSalariedEmployee.execute();
        LocalDate date = LocalDate.of(2017, 11, 30);
        //when
        PaydayIService paydayTransaction = new PaydayIService(date);
        paydayTransaction.execute();
        //then
        Paycheck paycheck = paydayTransaction.getPaycheck(employeeId);
        Assert.assertNotNull(paycheck);
        Assert.assertEquals(date, paycheck.getPayDate());
        Assert.assertEquals(1000.00, paycheck.getGrossPay(), 0.001);
        Assert.assertEquals("Hold", paycheck.getDisposition());
        Assert.assertEquals(0.00, paycheck.getDeductions(), 0.001);
        Assert.assertEquals(1000.00, paycheck.getNetPay(), 0.001);
    }

    /**
     * 错误日期支付带薪雇员薪水
     */
    @Test
    public void should_pay_single_salaried_employee_on_wrong_date() {
        //give
        int employeeId = 1;
        AddSalariedEmployeeService addSalariedEmployee = new AddSalariedEmployeeService(employeeId, "Name", "Address", 1000.00);
        addSalariedEmployee.execute();
        LocalDate date = LocalDate.of(2017, 11, 29);
        //when
        PaydayIService paydayTransaction = new PaydayIService(date);
        paydayTransaction.execute();
        //then
        Paycheck paycheck = paydayTransaction.getPaycheck(employeeId);
        Assert.assertNull(paycheck);
    }

    /**
     * 支付钟点工薪水,无时间卡
     */
    @Test
    public void should_pay_single_hourly_employee_no_time_cards() {
        //give
        int employeeId = 1;
        AddHourlyEmployeeService addHourlyEmployee = new AddHourlyEmployeeService(employeeId, "name", "address", 50.20);
        addHourlyEmployee.execute();
        LocalDate date = LocalDate.of(2017, 12, 1);//Friday
        //when
        PaydayIService paydayTransaction = new PaydayIService(date);
        paydayTransaction.execute();
        //then
        ValidatePaycheck(paydayTransaction, employeeId, date, 0.0);
    }

    private void ValidatePaycheck(PaydayIService paydayTransaction, int employeeId, LocalDate date, double pay) {
        Paycheck paycheck = paydayTransaction.getPaycheck(employeeId);
        Assert.assertNotNull(paycheck);
        Assert.assertEquals(date, paycheck.getPayDate());
        Assert.assertEquals(pay, paycheck.getGrossPay(), 0.001);
        Assert.assertEquals(0.00, paycheck.getDeductions(), 0.001);
        Assert.assertEquals(pay, paycheck.getNetPay(), 0.001);
    }

    /**
     * 支付钟点工薪水,一个不超过8小时时间卡
     */
    @Test
    public void should_pay_single_hourly_employee_one_time_cards() {
        //give
        int employeeId = 1;
        AddHourlyEmployeeService addHourlyEmployee = new AddHourlyEmployeeService(employeeId, "name", "address", 50.20);
        addHourlyEmployee.execute();
        LocalDate date = LocalDate.of(2017, 12, 1);//Friday
        TimeCardIService timeCardTransaction = new TimeCardIService(date, 4.0, employeeId);
        timeCardTransaction.execute();
        //when
        PaydayIService paydayTransaction = new PaydayIService(date);
        paydayTransaction.execute();
        //then
        ValidatePaycheck(paydayTransaction, employeeId, date, 200.80);
    }

    /**
     * 支付钟点工薪水,一个超过8小时时间卡
     */
    @Test
    public void should_pay_single_hourly_employee_over_time_one_time_cards() {
        //give
        int employeeId = 1;
        AddHourlyEmployeeService addHourlyEmployee = new AddHourlyEmployeeService(employeeId, "name", "address", 50.20);
        addHourlyEmployee.execute();
        LocalDate date = LocalDate.of(2017, 12, 1);//Friday
        TimeCardIService timeCardTransaction = new TimeCardIService(date, 9.0, employeeId);
        timeCardTransaction.execute();
        //when
        PaydayIService paydayTransaction = new PaydayIService(date);
        paydayTransaction.execute();
        //then
        ValidatePaycheck(paydayTransaction, employeeId, date, 476.9);//50.20 * 8 + 50.2 * 1.5
    }

    /**
     * 支付钟点工薪水,错误日期
     */
    @Test
    public void should_pay_single_hourly_employee_wrong_date() {
        //give
        int employeeId = 1;
        AddHourlyEmployeeService addHourlyEmployee = new AddHourlyEmployeeService(employeeId, "name", "address", 50.20);
        addHourlyEmployee.execute();
        LocalDate date = LocalDate.of(2017, 12, 2);//Friday
        TimeCardIService timeCardTransaction = new TimeCardIService(date, 9.0, employeeId);
        timeCardTransaction.execute();
        //when
        PaydayIService paydayTransaction = new PaydayIService(date);
        paydayTransaction.execute();
        //then
        Assert.assertNull(paydayTransaction.getPaycheck(employeeId));
    }

    /**
     * 支付钟点工薪水,两个时间卡
     */
    @Test
    public void should_pay_single_hourly_employee_two_time_cards() {
        //give
        int employeeId = 1;
        AddHourlyEmployeeService addHourlyEmployee = new AddHourlyEmployeeService(employeeId, "name", "address", 50.20);
        addHourlyEmployee.execute();
        LocalDate date = LocalDate.of(2017, 12, 1);//Friday
        TimeCardIService timeCardTransaction = new TimeCardIService(date, 9.0, employeeId);
        timeCardTransaction.execute();
        TimeCardIService timeCardTransaction2 = new TimeCardIService(date.plusDays(-1), 5.0, employeeId);
        timeCardTransaction2.execute();
        //when
        PaydayIService paydayTransaction = new PaydayIService(date);
        paydayTransaction.execute();
        //then
        ValidatePaycheck(paydayTransaction, employeeId, date, 727.9);//50.20 * 8 + 50.2 * 1.5 + 5 * 50.2
    }

    /**
     * 支付钟点工薪水,两个时期内时间卡
     */
    @Test
    public void should_pay_single_hourly_employee_time_cards_spanning_two_periods() {
        //give
        int employeeId = 1;
        AddHourlyEmployeeService addHourlyEmployee = new AddHourlyEmployeeService(employeeId, "name", "address", 50.20);
        addHourlyEmployee.execute();
        LocalDate date = LocalDate.of(2017, 12, 1);//Friday
        TimeCardIService timeCardTransaction = new TimeCardIService(date, 9.0, employeeId);
        timeCardTransaction.execute();
        TimeCardIService timeCardTransaction2 = new TimeCardIService(date.plusDays(-9), 5.0, employeeId);
        timeCardTransaction2.execute();
        //when
        PaydayIService paydayTransaction = new PaydayIService(date);
        paydayTransaction.execute();
        //then
        ValidatePaycheck(paydayTransaction, employeeId, date, 476.9);//50.20 * 8 + 50.2 * 1.5
    }

    /**
     * 支付销售薪水，无销售凭条
     */
    @Test
    public void should_pay_single_commission_employee_no_sales_receipt() {
        //give
        int employeeId = 1;
        AddCommissionedEmployeeService addCommissionedEmployee = new AddCommissionedEmployeeService(employeeId, "name", "address", 1000.00, 125.0);
        addCommissionedEmployee.execute();
        LocalDate date = LocalDate.of(2017, 12, 1);//Friday
        //when
        PaydayIService paydayTransaction = new PaydayIService(date);
        paydayTransaction.execute();
        //then
        ValidatePaycheck(paydayTransaction, employeeId, date, 0);
    }

    //todo 销售凭条支付

    /**
     * 协会服务费扣除
     */
    @Test
    public void should_salaried_union_member_dues() {
        //give
        int employeeId = 1;
        AddSalariedEmployeeService addSalariedEmployee = new AddSalariedEmployeeService(employeeId, "", "", 1000.00);
        addSalariedEmployee.execute();
        int memberId = 71;
        ChangeMemberIService changeMemberTransaction = new ChangeMemberIService(employeeId, memberId, 29.0);
        changeMemberTransaction.execute();
        LocalDate date = LocalDate.of(2017, 11, 30);
        //when
        PaydayIService paydayTransaction = new PaydayIService(date);
        paydayTransaction.execute();
        //then
        Paycheck paycheck = paydayTransaction.getPaycheck(employeeId);
        Assert.assertNotNull(paycheck);
        Assert.assertEquals(date, paycheck.getPayDate());
        Assert.assertEquals(1000.00, paycheck.getGrossPay(), 0.001);
        Assert.assertEquals(116.00, paycheck.getDeductions(), 0.001);
        Assert.assertEquals(884.00, paycheck.getNetPay(), 0.001);
    }

    /**
     * 扣除协会服务费用
     */
    @Test
    public void should_union_member_service_charge() {
        //give
        int employeeId = 1;
        AddHourlyEmployeeService addHourlyEmployee = new AddHourlyEmployeeService(employeeId, "", "", 25.00);
        addHourlyEmployee.execute();
        int memberId = 77;
        ChangeMemberIService changeMemberTransaction = new ChangeMemberIService(employeeId, memberId, 9.43);
        changeMemberTransaction.execute();
        LocalDate date = LocalDate.of(2017, 12, 1);
        ServiceChargeIService serviceChargeTransaction = new ServiceChargeIService(memberId, date, 19.26);
        serviceChargeTransaction.execute();
        TimeCardIService timeCardTransaction = new TimeCardIService(date, 8.0, employeeId);
        timeCardTransaction.execute();
        //when
        PaydayIService paydayTransaction = new PaydayIService(date);
        paydayTransaction.execute();
        //then
        Paycheck paycheck = paydayTransaction.getPaycheck(employeeId);
        Assert.assertNotNull(paycheck);
        Assert.assertEquals(date, paycheck.getPayDate());
        Assert.assertEquals(25.00 * 8, paycheck.getGrossPay(), 0.001);
        Assert.assertEquals(9.43 + 19.26, paycheck.getDeductions(), 0.001);
        Assert.assertEquals(25.00 * 8 - (9.43 + 19.26), paycheck.getNetPay(), 0.001);
    }

    /**
     * 扣除协会服务费用,支付期外服务费用不被扣除
     */
    @Test
    public void should_union_member_service_charge_spanning() {
        //give
        int employeeId = 1;
        AddHourlyEmployeeService addHourlyEmployee = new AddHourlyEmployeeService(employeeId, "", "", 25.00);
        addHourlyEmployee.execute();
        int memberId = 77;
        ChangeMemberIService changeMemberTransaction = new ChangeMemberIService(employeeId, memberId, 9.43);
        changeMemberTransaction.execute();
        LocalDate date = LocalDate.of(2017, 12, 1);
        ServiceChargeIService serviceChargeTransaction = new ServiceChargeIService(memberId, date, 19.26);
        serviceChargeTransaction.execute();
        ServiceChargeIService serviceChargeTransaction2 = new ServiceChargeIService(memberId, date.plusDays(20), 10);
        serviceChargeTransaction2.execute();
        TimeCardIService timeCardTransaction = new TimeCardIService(date, 8.0, employeeId);
        timeCardTransaction.execute();
        //when
        PaydayIService paydayTransaction = new PaydayIService(date);
        paydayTransaction.execute();
        //then
        Paycheck paycheck = paydayTransaction.getPaycheck(employeeId);
        Assert.assertNotNull(paycheck);
        Assert.assertEquals(date, paycheck.getPayDate());
        Assert.assertEquals(25.00 * 8, paycheck.getGrossPay(), 0.001);
        Assert.assertEquals(9.43 + 19.26, paycheck.getDeductions(), 0.001);
        Assert.assertEquals(25.00 * 8 - (9.43 + 19.26), paycheck.getNetPay(), 0.001);
    }
}
