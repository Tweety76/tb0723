import java.time.LocalDate;

public class Checkout {
    public String tool_code;
    public int num_rental_days;
    public LocalDate check_out_date;
    public LocalDate due_date;
    public int num_charge_days; //the number of days the item is rented - weekends
    public double pre_discount_charge_rate;
    public int discount_percent;
    public double discount_charge_rate;
    public double discount_amount;   //pre-discount -
    public double final_charge_amount;  //final amount charged to customer


    public Checkout(String toolCode, int numRentalDays, int discountPercent, LocalDate checkOutDate){
        tool_code = toolCode;
        num_rental_days = numRentalDays;
        discount_percent = discountPercent;
        check_out_date = checkOutDate;
        due_date = calculateDueDate(check_out_date, num_rental_days);
    }


    public LocalDate calculateDueDate(LocalDate checkOutDate, int numRentalDays){
        return checkOutDate.plusDays(numRentalDays);
    }
}
