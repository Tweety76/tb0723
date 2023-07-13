import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DemonstrationTest {

    @Test
    void test1() {
        assertThrows(IllegalArgumentException.class, () -> new Checkout("JAKR",5,101, LocalDate.of(2015,9,3)));
    }


    @Test
    void test2() throws IllegalArgumentException {
        var rental = new Checkout("LADW",3,10,LocalDate.of(2020,7,2));
        //rental.printRentalAgreement();
        assertEquals("LADW",rental.tool_code);
        assertEquals("Ladder",rental.rental_tool.tool_type);
        assertEquals("Werner",rental.rental_tool.tool_brand);
        assertEquals(3,rental.num_rental_days);
        assertEquals(LocalDate.of(2020,7,2),rental.check_out_date);
        assertEquals(LocalDate.of(2020,7,5),rental.due_date);
        assertEquals(1.99,rental.rental_tool.daily_rental_charge);
        assertEquals(2,rental.num_charge_days);
        assertEquals(3.98,rental.pre_discount_charge_amount); //check the pre-discount charge amount, that it has only 2 decimal places, and rounded up
        assertEquals(10,rental.discount_percent);
        assertEquals(0.40,rental.discount_amount); //check the pre-discount charge amount, that it has only 2 decimal places, and rounded up
        assertEquals(3.58,rental.final_charge_amount);
    }

    @Test
    void test3() throws IllegalArgumentException {
        var rental = new Checkout("CHNS",5,25,LocalDate.of(2015,7,2));
        //rental.printRentalAgreement();
        assertEquals("CHNS",rental.tool_code);
        assertEquals("Chainsaw",rental.rental_tool.tool_type);
        assertEquals("Stihl",rental.rental_tool.tool_brand);
        assertEquals(5,rental.num_rental_days);
        assertEquals(LocalDate.of(2015,7,2),rental.check_out_date);
        assertEquals(LocalDate.of(2015,7,7),rental.due_date);
        assertEquals(1.49,rental.rental_tool.daily_rental_charge);
        assertEquals(3,rental.num_charge_days);
        assertEquals(4.47,rental.pre_discount_charge_amount); //check the pre-discount charge amount, that it has only 2 decimal places, and rounded up
        assertEquals(25,rental.discount_percent);
        assertEquals(1.12,rental.discount_amount); //check the pre-discount charge amount, that it has only 2 decimal places, and rounded up
        assertEquals(3.35,rental.final_charge_amount);
    }

    @Test
    void test4() throws IllegalArgumentException {
        var rental = new Checkout("JAKD",6,0,LocalDate.of(2015,9,3));
        //rental.printRentalAgreement();
        assertEquals("JAKD",rental.tool_code);
        assertEquals("Jackhammer",rental.rental_tool.tool_type);
        assertEquals("DeWalt",rental.rental_tool.tool_brand);
        assertEquals(6,rental.num_rental_days);
        assertEquals(LocalDate.of(2015,9,3),rental.check_out_date);
        assertEquals(LocalDate.of(2015,9,9),rental.due_date);
        assertEquals(2.99,rental.rental_tool.daily_rental_charge);
        assertEquals(3,rental.num_charge_days);
        assertEquals(8.97,rental.pre_discount_charge_amount); //check the pre-discount charge amount, that it has only 2 decimal places, and rounded up
        assertEquals(0,rental.discount_percent);
        assertEquals(0,rental.discount_amount); //check the pre-discount charge amount, that it has only 2 decimal places, and rounded up
        assertEquals(8.97,rental.final_charge_amount);
    }

    @Test
    void test5() throws IllegalArgumentException {
        var rental = new Checkout("JAKR",9,0,LocalDate.of(2015,7,2));
        //rental.printRentalAgreement();
        assertEquals("JAKR",rental.tool_code);
        assertEquals("Jackhammer",rental.rental_tool.tool_type);
        assertEquals("Ridgid",rental.rental_tool.tool_brand);
        assertEquals(9,rental.num_rental_days);
        assertEquals(LocalDate.of(2015,7,2),rental.check_out_date);
        assertEquals(LocalDate.of(2015,7,11),rental.due_date);
        assertEquals(2.99,rental.rental_tool.daily_rental_charge);
        assertEquals(5,rental.num_charge_days);
        assertEquals(14.95,rental.pre_discount_charge_amount); //check the pre-discount charge amount, that it has only 2 decimal places, and rounded up
        assertEquals(0,rental.discount_percent);
        assertEquals(0,rental.discount_amount); //check the pre-discount charge amount, that it has only 2 decimal places, and rounded up
        assertEquals(14.95,rental.final_charge_amount);
    }

    @Test
    void test6() throws IllegalArgumentException {
        var rental = new Checkout("JAKR",4,50,LocalDate.of(2020,7,2));
        //rental.printRentalAgreement();
        assertEquals("JAKR",rental.tool_code);
        assertEquals("Jackhammer",rental.rental_tool.tool_type);
        assertEquals("Ridgid",rental.rental_tool.tool_brand);
        assertEquals(4,rental.num_rental_days);
        assertEquals(LocalDate.of(2020,7,2),rental.check_out_date);
        assertEquals(LocalDate.of(2020,7,6),rental.due_date);
        assertEquals(2.99,rental.rental_tool.daily_rental_charge);
        assertEquals(1,rental.num_charge_days);
        assertEquals(2.99,rental.pre_discount_charge_amount); //check the pre-discount charge amount, that it has only 2 decimal places, and rounded up
        assertEquals(50,rental.discount_percent);
        assertEquals(1.50,rental.discount_amount); //check the pre-discount charge amount, that it has only 2 decimal places, and rounded up
        assertEquals(1.49,rental.final_charge_amount);
    }
}
