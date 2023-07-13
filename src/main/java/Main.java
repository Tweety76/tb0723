import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner kb = new Scanner(System.in);
        String[][] available_tools = {
                {"Tool Code","Tool Type","Brand"},
                {"---------","---------","-----"},
                {"CHNS","Chainsaw","Stihl"},
                {"LADW","Ladder","Werner"},
                {"JAKD","Jackhammer","DeWalt"},
                {"JAKR","Jackhammer","Ridgid"}};

        String tool_code;
        String tool_type = "";
        int num_rental_days = 0;
        int discount_percent = 0;
        String date;
        LocalDate check_out_date = null;
        boolean isValid = false;

        //get the tool code from the employee
        System.out.println("""

                Welcome to the Hardware Store Demo!
                Please enter the Tool Code of the item that the customer wants to rent
                """);
        for (String[] row:available_tools) {
            System.out.format("%15s%15s%15s%n", row);
        }

        do{
            tool_code = kb.nextLine();
            for (int i = 2;i <=5;i++){
                if(tool_code.equalsIgnoreCase(available_tools[i][0])){
                    isValid = true;
                    tool_type = available_tools[i][1];
                    break;
                }
            }
            if(!isValid){
                System.out.println("That is an invalid tool code. Please enter a different tool code.");
            }
        }while (!isValid);
        isValid = false;

        //get the number of rental days from the employee
        System.out.println("How many days is the " + tool_type + " being rented? Please enter whole numbers only.");
        do{
            if(kb.hasNextInt()) {
                num_rental_days = kb.nextInt();
                if(num_rental_days > 0){
                    isValid = true;
                    kb.nextLine();
                }
                else {
                    System.out.println("A tool cannot be rented for less than 1 day. Please enter a number of days greater than 0.");
                    kb.nextLine();
                }
            } else {
                System.out.println("This is not a whole number. Please enter a whole number.");
                kb.nextLine();
            }
        }while (!isValid);
        isValid = false;

        //get the date from the employee
        System.out.println("Please enter today's date in the following format: MM/dd/yyyy");
        do{
            date = kb.nextLine();
            try{
                check_out_date = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                isValid = true;
            }catch (DateTimeParseException E){
                System.out.println("This is not in the correct date format. Please enter the date as MM/dd/yyyy.");
                //kb.nextLine();
            }
        }while(!isValid);
        isValid = false;


        //get the discount amount from the employee
        System.out.println("Please enter the amount of discount that will be applied to this order as a whole number from 0-100 (e.g. 20 = 20%)");
        do{
            if(kb.hasNextInt()) {
                discount_percent = kb.nextInt();
                if((discount_percent >= 0 && discount_percent <= 100)){
                    isValid = true;
                    kb.nextLine();
                }
                else {
                    System.out.println("That is not a valid value. Please enter a number between 0 and 100.");
                    kb.nextLine();
                }
            } else {
                System.out.println("This is not a whole number. Please enter a whole number.");
                kb.nextLine();
            }
        }while (!isValid);



        System.out.println("Thank you please wait while the Rental Agreement is generated\n");
        Checkout tool_rental = new Checkout(tool_code,num_rental_days,discount_percent,check_out_date);
        TimeUnit.SECONDS.sleep(2);
        tool_rental.printRentalAgreement();
        System.out.println("\nPlease enjoy your " + tool_type + " for " + num_rental_days + " days.\n" +
                "We will see you on " + tool_rental.due_date.format(DateTimeFormatter.ofPattern("MM/dd/yy")) + " for your return.");
    }
}