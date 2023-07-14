/**
 * This class creates a rental agreement for a tool. The available tools are in Tools.java.
 *
 * @author Ty Bird
 * @since 2023-07-11
 */

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Checkout {
    public String tool_code;
    public int num_rental_days;
    public LocalDate check_out_date;
    public LocalDate due_date;
    public int num_charge_days; //the number of days the item is being charged
    public double pre_discount_charge_amount;
    public int discount_percent;
    public double discount_amount;
    public double final_charge_amount;  //final amount charged to customer
    Tools rental_tool;

    /**
     * Constructor for the Checkout class that generates a rental agreement for a tool
     * @param toolCode Tool Code for the tool being rented
     * @param numRentalDays Number of days the tool is being rented
     * @param discountPercent Amount of discount being applied to the final charge amount. Represented as a percentage e.g.20%
     * @param checkOutDate The date that the tool is being checked out on
     * @throws IllegalArgumentException Throws exception if the number of rental days is less than 1
     * @throws IllegalArgumentException Throws exception if the discount percent is not between 0-100
     */
    public Checkout(String toolCode, int numRentalDays, int discountPercent, LocalDate checkOutDate) throws IllegalArgumentException {
        if(numRentalDays < 1)
            throw new IllegalArgumentException("The number of rental days cannot be less than 1");
        if(discountPercent < 0 || discountPercent > 100)
            throw new IllegalArgumentException("The discount percentage needs to be between 0 and 100");
        rental_tool = new Tools(toolCode);
        tool_code = rental_tool.tool_code;
        num_rental_days = numRentalDays;
        discount_percent = discountPercent;
        check_out_date = checkOutDate;
        due_date = calculateDueDate(check_out_date, num_rental_days);
        num_charge_days = calculateNumChargeDays(check_out_date, due_date, rental_tool);
        pre_discount_charge_amount = calculatePreDiscountChargeAmount(rental_tool,num_charge_days);
        discount_amount = calculateDiscountAmount(pre_discount_charge_amount,discount_percent);
        final_charge_amount = calculateFinalChargeAmount(pre_discount_charge_amount, discount_amount);
    }

    /**
     * Method to calculate the date that a tool is due to be returned
     * @param checkOutDate the date the tool is rented
     * @param numRentalDays the number of days the tool is rented
     * @return LocalDate Returns the date the tool needs to be returned
     */
    public LocalDate calculateDueDate(LocalDate checkOutDate, int numRentalDays){
        return checkOutDate.plusDays(numRentalDays);
    }

    /**
     * Tools are not charged every day. Some tools are free on weekends and some are free on holidays. This method calculates the number of days a tool
     * @param checkOutDate the date the tool is rented
     * @param dueDate the date the tool needs to be returned
     * @param tool reference to the tool being rented
     * @return int Returns the number of days that the customer will be charged
     */
    public int calculateNumChargeDays(LocalDate checkOutDate, LocalDate dueDate, Tools tool){
        int count = 0;
        LocalDate date = checkOutDate;
        ArrayList<LocalDate> holidays = getHolidays(checkOutDate.getYear(),dueDate.getYear());
        while (date.isBefore(dueDate)){
            date = date.plusDays(1);
            if(holidays.contains(date)){    //weekend check
                if(tool.holiday_charge)
                    count++;
            } else if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {    //weekend check
                if(tool.weekend_charge)
                    count++;
            } else if (date.getDayOfWeek().equals(DayOfWeek.MONDAY) || date.getDayOfWeek().equals(DayOfWeek.TUESDAY) || date.getDayOfWeek().equals(DayOfWeek.WEDNESDAY) || date.getDayOfWeek().equals(DayOfWeek.THURSDAY) || date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {  //weekday check
                if(tool.weekday_charge)
                    count++;
            }
        }
        return count;
    }

    /**
     * Tools are not charged every day. Some tools are free on weekends and some are free on holidays. This method finds the list of valid holidays that some tools will not be charged on
     * I have added the return due date year in case of an extended rental period since no limits were placed on length of rental.
     * Current list of holidays includes Independence day which will be observed on the closest weekday if it falls on the weekend and Labor day
     * @param checkOutYear this is the year field from the date of check out
     * @param dueDateYear This is the year field from the date of return
     * @return ArrayList<LocalDate> Returns a list of holiday dates
     */
    public ArrayList<LocalDate> getHolidays(int checkOutYear, int dueDateYear){
        ArrayList<LocalDate> holidays = new ArrayList<>();

        for(int i = checkOutYear;i<= dueDateYear;i++) {
            //Independence Day
            LocalDate ind_day = LocalDate.of(i, 7, 4);
            if (ind_day.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                ind_day = ind_day.minusDays(1);
            } else if (ind_day.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                ind_day = ind_day.plusDays(1);
            }
            holidays.add(ind_day);


            //Labor Day
            LocalDate labor_day = LocalDate.of(i, 9, 1);
            // for(int i = 0; i < 7; i++){
            while (labor_day.getDayOfMonth() <= 7) {
                if (labor_day.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
                    break;
                }
                labor_day = labor_day.plusDays(1);
            }
            holidays.add(labor_day);
        }

        return holidays;
    }

    /**
     * Method to calculate the charge amount before any discount is applied
     * daily rate * number of charge days
     * @param tools reference to the tool being rented
     * @param numChargeDays the number of days that the customer will be charged
     * @return double the charge amount before any discount is applied rounded up a half penny
     */
    public double calculatePreDiscountChargeAmount(Tools tools, int numChargeDays){
        double pre_discount_charge_amount = tools.daily_rental_charge * numChargeDays;
        pre_discount_charge_amount = Math.floor((pre_discount_charge_amount*100)+.5)/100; //round up and leave only 2 decimal points
        return pre_discount_charge_amount;
    }

    /**
     * Method to calculate the amount discounted from the final charge
     * pre discount amount * discount percentage
     * @param preDiscountChargeAmount the charge amount before any discount is applied rounded
     * @param discountPercent The percent discounted from the final charge
     * @return double The amount discounted from final charge
     */
    public double calculateDiscountAmount(double preDiscountChargeAmount, int discountPercent){
        double discount_amount = preDiscountChargeAmount * (discountPercent/100.0);
        discount_amount = Math.floor((discount_amount*100)+.5)/100; //round up and leave only 2 decimal points
        return discount_amount;
    }

    /**
     * Method to calculate the final charge amount after the discount
     * pre discount charge amount - discount amount
     * @param preDiscountChargeAmount The charge amount before any discount is applied rounded
     * @param discountAmount The amount discounted from final charge
     * @return double The final charge amount rounded up half a penny
     */
    public double calculateFinalChargeAmount(double preDiscountChargeAmount, double discountAmount){
        double final_charge_amount = preDiscountChargeAmount - discountAmount;
        final_charge_amount = Math.floor((final_charge_amount*100)+.5)/100; //round up and leave only 2 decimal points
        return final_charge_amount;
    }

    /**
     * Method to print the Rental Agreement with all the below items:
     * Tool Code, Tool Type, Tool Brand, Rental Days, Check Out Date, Due Date, Daily Rental Charge, Charge Days, Pre-Discount Amount, Discount Percent, Discount Amount, Final Charge
     */
    public void printRentalAgreement(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        String rental_agreement =
                "Tool Code: " + tool_code +
                "\nTool Type: " + rental_tool.tool_type +
                "\nTool Brand: " + rental_tool.tool_brand +
                "\nRental Days: " + num_rental_days + " Days" +
                "\nCheck Out Date: " + check_out_date.format(dateTimeFormatter) +
                "\nDue Date: " + due_date.format(dateTimeFormatter) +
                "\nDaily Rental Charge: $" + decimalFormat.format(rental_tool.daily_rental_charge) + "/day" +
                "\nCharge Days: " + num_charge_days + " Days" +
                "\nPre-Discount Amount: $" + decimalFormat.format(pre_discount_charge_amount) +
                "\nDiscount Percent: " + discount_percent + "%" +
                "\nDiscount Amount: $" + decimalFormat.format(discount_amount) +
                "\nFinal Charge: $" + decimalFormat.format(final_charge_amount);

        System.out.println(rental_agreement);
    }
}
