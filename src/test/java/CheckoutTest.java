import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    @Test
    void getHolidayTest1YearSpan(){
        var rental = new Checkout("LADW",3,10,LocalDate.of(2020,7,2));
        ArrayList<LocalDate> holidays = rental.getHolidays(rental.check_out_date.getYear(),rental.due_date.getYear());
        //System.out.println(holidays);
        //System.out.println(holidays.get(0).toString().substring(0,4));
        assertEquals(2,holidays.size());    //check that for 1 year, 2 holidays are showing up
        assertEquals(Integer.toString(rental.check_out_date.getYear()),holidays.get(0).toString().substring(0,4)); //checking that the year of the checkout date matches the year from the holidays
    }

    @Test
    void getHolidayTest2YearSpan(){
        var rental = new Checkout("LADW",3,10,LocalDate.of(2020,12,30));
        ArrayList<LocalDate> holidays = rental.getHolidays(rental.check_out_date.getYear(),rental.due_date.getYear());
        //System.out.println(holidays);
        //System.out.println(holidays.get(3).toString().substring(0,4));
        assertEquals(4,holidays.size());    //check that for 1 year, 2 holidays are showing up
        assertEquals(Integer.toString(rental.check_out_date.getYear()),holidays.get(0).toString().substring(0,4));  //checking that the year of the checkout date matches the year from the first holiday
        assertEquals(Integer.toString(rental.due_date.getYear()),holidays.get(3).toString().substring(0,4));    //checking that the year of the due date matches the year from the last holiday
    }

    //checking that the date for Independence Day gets moved to Friday if it's on Saturday
    @Test
    void getHolidayTestIndependenceDayOnSaturday(){
        var rental = new Checkout("LADW",4,10,LocalDate.of(2020,7,2));
        assertEquals(LocalDate.of(2020,7,3), rental.getHolidays(rental.check_out_date.getYear(),rental.due_date.getYear()).get(0));
        assertEquals(DayOfWeek.FRIDAY,rental.getHolidays(rental.check_out_date.getYear(),rental.due_date.getYear()).get(0).getDayOfWeek());
    }

    //checking that the date for Independence Day gets moved to Monday if it's on Sunday
    @Test
    void getHolidayTestIndependenceDayOnSunday(){
        var rental = new Checkout("LADW",4,10,LocalDate.of(2021,7,2));
        assertEquals(LocalDate.of(2021,7,5), rental.getHolidays(rental.check_out_date.getYear(),rental.due_date.getYear()).get(0));
        assertEquals(DayOfWeek.MONDAY,rental.getHolidays(rental.check_out_date.getYear(),rental.due_date.getYear()).get(0).getDayOfWeek());
    }

    @Test
    void getHolidayTestIndependenceDayOnWeekday(){
        var rental = new Checkout("LADW",4,10,LocalDate.of(2023,7,2));
        assertEquals(LocalDate.of(2023,7,4), rental.getHolidays(rental.check_out_date.getYear(),rental.due_date.getYear()).get(0));
        assertEquals(DayOfWeek.TUESDAY,rental.getHolidays(rental.check_out_date.getYear(),rental.due_date.getYear()).get(0).getDayOfWeek());
    }

    @Test
    void getHolidayTestLaborDayOn1st(){
        var rental = new Checkout("LADW",4,10,LocalDate.of(2025,8,30));
        assertEquals(LocalDate.of(2025,9,1), rental.getHolidays(rental.check_out_date.getYear(),rental.due_date.getYear()).get(1));
        assertEquals(DayOfWeek.MONDAY,rental.getHolidays(rental.check_out_date.getYear(),rental.due_date.getYear()).get(1).getDayOfWeek());
    }

    @Test
    void getHolidayTestLaborDayOn7th(){
        var rental = new Checkout("LADW",10,10,LocalDate.of(2026,8,30));
        assertEquals(LocalDate.of(2026,9,7), rental.getHolidays(rental.check_out_date.getYear(),rental.due_date.getYear()).get(1));
        assertEquals(DayOfWeek.MONDAY,rental.getHolidays(rental.check_out_date.getYear(),rental.due_date.getYear()).get(1).getDayOfWeek());
    }

    @Test
    void getHolidayTestLaborDay(){
        var rental = new Checkout("LADW",10,10,LocalDate.of(2018,8,30));
        assertEquals(LocalDate.of(2018,9,3), rental.getHolidays(rental.check_out_date.getYear(),rental.due_date.getYear()).get(1));
        assertEquals(DayOfWeek.MONDAY,rental.getHolidays(rental.check_out_date.getYear(),rental.due_date.getYear()).get(1).getDayOfWeek());
    }

    @Test
    void calculateDueDate0RentalDays() {
        assertThrows(IllegalArgumentException.class, () -> new Checkout("JAKR",0,0, LocalDate.of(2015,9,3)));
    }

    @Test
    void calculateDueDateMinus1RentalDays() {
        assertThrows(IllegalArgumentException.class, () -> new Checkout("LADW",-1,10,LocalDate.of(2018,8,30)));
    }

    @Test
    void calculateDueDate7RentalDays() {
        var rental = new Checkout("LADW",7,10,LocalDate.of(2018,8,30));
        assertEquals(7,rental.num_rental_days);
    }

    @Test
    void calculateDueDate1300RentalDays() {
        var rental = new Checkout("LADW",1300,10,LocalDate.of(2018,8,30));
        assertEquals(1300,rental.num_rental_days);
    }

    @Test
    void calculateNumChargeDaysLADW() {
        var rental = new Checkout("LADW",10,10,LocalDate.of(2018,5,3));
        assertEquals(10,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysCHNS() {
        var rental = new Checkout("CHNS",10,10,LocalDate.of(2018,5,3));
        assertEquals(6,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysJAKD() {
        var rental = new Checkout("JAKD",10,10,LocalDate.of(2018,5,3));
        assertEquals(6,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysJAKR() {
        var rental = new Checkout("JAKR",10,10,LocalDate.of(2018,5,3));
        assertEquals(6,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysIndependenceDayOnSaturdayLADW() {
        var rental = new Checkout("LADW",10,10,LocalDate.of(2020,7,2));
        assertEquals(9,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysIndependenceDayOnSundayLADW() {
        var rental = new Checkout("LADW",10,10,LocalDate.of(2021,7,2));
        assertEquals(9,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysIndependenceDayOnWeekdayLADW() {
        var rental = new Checkout("LADW",10,10,LocalDate.of(2023,7,2));
        assertEquals(9,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysIndependenceDayOnSaturdayCHNS() {
        var rental = new Checkout("CHNS",10,10,LocalDate.of(2020,7,2));
        assertEquals(6,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysIndependenceDayOnSundayCHNS() {
        var rental = new Checkout("CHNS",10,10,LocalDate.of(2021,7,2));
        assertEquals(6,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysIndependenceDayOnWeekdayCHNS() {
        var rental = new Checkout("CHNS",10,10,LocalDate.of(2023,7,2));
        assertEquals(8,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysIndependenceDayOnSaturdayJAKD() {
        var rental = new Checkout("JAKD",10,10,LocalDate.of(2020,7,2));
        assertEquals(5,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysIndependenceDayOnSundayJAKD() {
        var rental = new Checkout("JAKD",10,10,LocalDate.of(2021,7,2));
        assertEquals(5,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysIndependenceDayOnWeekdayJAKD() {
        var rental = new Checkout("JAKD",10,10,LocalDate.of(2023,7,2));
        assertEquals(7,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysIndependenceDayOnSaturdayJAKR() {
        var rental = new Checkout("JAKR",10,10,LocalDate.of(2020,7,2));
        assertEquals(5,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysIndependenceDayOnSundayJAKR() {
        var rental = new Checkout("JAKR",10,10,LocalDate.of(2021,7,2));
        assertEquals(5,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysIndependenceDayOnWeekdayJAKR() {
        var rental = new Checkout("JAKR",10,10,LocalDate.of(2023,7,2));
        assertEquals(7,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysLaborDayLADW() {
        var rental = new Checkout("LADW",10,10,LocalDate.of(2018,8,30));
        assertEquals(9,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysLaborDayCHNS() {
        var rental = new Checkout("CHNS",10,10,LocalDate.of(2018,8,30));
        assertEquals(6,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysLaborDayJAKD() {
        var rental = new Checkout("JAKD",10,10,LocalDate.of(2018,8,30));
        assertEquals(5,rental.num_charge_days);
    }

    @Test
    void calculateNumChargeDaysLaborDayJAKR() {
        var rental = new Checkout("JAKR",10,10,LocalDate.of(2018,8,30));
        assertEquals(5,rental.num_charge_days);
    }


    @Test
    void calculateDiscountAndFinalChargeDiscountPercentMinus(){
        assertThrows(IllegalArgumentException.class, () -> new Checkout("JAKR",10,-1, LocalDate.of(2015,9,3)));
    }


    @Test
    void calculateDiscountAndFinalChargeDiscountPercentOver100(){
        assertThrows(IllegalArgumentException.class, () -> new Checkout("JAKR",10,101, LocalDate.of(2015,9,3)));
    }

    @Test
    void calculateDiscountAndFinalChargeCHNS(){
        var rental = new Checkout("CHNS",10,23,LocalDate.of(2018,8,30));
        assertEquals(8.94,rental.pre_discount_charge_amount);
        assertEquals(2.06,rental.discount_amount);  //check that the value is 2.06 and not 2.0562
        assertNotEquals(2.0562,rental.discount_amount); //check that the value is 2.06 and not 2.0562
        assertNotEquals(2.05,rental.discount_amount);   //check that the value is 2.06 and not 2.05 (Rounding)
        assertEquals(6.88,rental.final_charge_amount);
    }

    @Test
    void calculateDiscountAndFinalChargeLADW(){
        var rental = new Checkout("LADW",10,23,LocalDate.of(2018,8,30));
        assertEquals(17.91,rental.pre_discount_charge_amount);
        assertEquals(4.12,rental.discount_amount);  //check that the value is 4.12 and not 4.1193
        assertNotEquals(4.1193,rental.discount_amount); //check that the value is 4.12 and not 4.1193
        assertNotEquals(4.11,rental.discount_amount);   //check that the value is 4.12 and not 4.11 (Rounding)
        assertEquals(13.79,rental.final_charge_amount);
    }

    @Test
    void calculateDiscountAndFinalChargeJAKD(){
        var rental = new Checkout("JAKD",10,23,LocalDate.of(2018,8,30));
        assertEquals(14.95,rental.pre_discount_charge_amount);
        assertEquals(3.44,rental.discount_amount);  //check that the value is 3.44 and not 3.4385
        assertNotEquals(3.4385,rental.discount_amount); //check that the value is 3.44 and not 3.4385
        assertNotEquals(3.43,rental.discount_amount);   //check that the value is 3.44 and not 3.43 (Rounding)
        assertEquals(11.51,rental.final_charge_amount);
    }

    @Test
    void calculateDiscountAndFinalChargeJAKR(){
        var rental = new Checkout("JAKR",10,23,LocalDate.of(2018,8,30));
        assertEquals(14.95,rental.pre_discount_charge_amount);
        assertEquals(3.44,rental.discount_amount);  //check that the value is 3.44 and not 3.4385
        assertNotEquals(3.4385,rental.discount_amount); //check that the value is 3.44 and not 3.4385
        assertNotEquals(3.43,rental.discount_amount);   //check that the value is 3.44 and not 3.43 (Rounding)
        assertEquals(11.51,rental.final_charge_amount);
    }

    @Test
    void calculateDiscountAndFinalCharge0PercentDiscount(){
        var rental = new Checkout("JAKR",10,0,LocalDate.of(2018,8,30));
        assertEquals(14.95,rental.pre_discount_charge_amount);
        assertEquals(0,rental.discount_amount);
        assertEquals(14.95,rental.final_charge_amount);
    }

    @Test
    void calculateDiscountAndFinalCharge100PercentDiscount(){
        var rental = new Checkout("JAKR",10,100,LocalDate.of(2018,8,30));
        assertEquals(14.95,rental.pre_discount_charge_amount);
        assertEquals(14.95,rental.discount_amount);
        assertEquals(0,rental.final_charge_amount);
    }
}