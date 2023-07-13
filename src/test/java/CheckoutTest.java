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

  /*  @Test
    void holidayTestLaborDayCheckOutOn1st(){
        var rental = new Checkout("LADW",4,10,LocalDate.of(2025,9,1));

        assertEquals(DayOfWeek.MONDAY,rental.getHolidays(rental.check_out_date.getYear(),rental.due_date.getYear()).get(0).getDayOfWeek());
    }*/

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
    void calculateDueDate() {
    }

    @Test
    void calculateNumChargeDays() {
    }
}