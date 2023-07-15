import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        String[][] available_tools = {
                {"Tool Code","Tool Type","Brand"},
                {"---------","---------","-----"},
                {"CHNS","Chainsaw","Stihl"},
                {"LADW","Ladder","Werner"},
                {"JAKD","Jackhammer","DeWalt"},
                {"JAKR","Jackhammer","Ridgid"}};

        String tool_code;
        String tool_type;
        int num_rental_days;
        int discount_percent;
        LocalDate check_out_date;

        //Collect info from the user
        tool_code = getToolCodeFromUser(available_tools);
        tool_type = getToolType(tool_code, available_tools);
        num_rental_days = getNumberOfRentalDaysFromUser(tool_type);
        check_out_date = getDateFromUser();
        discount_percent = getDiscountPercentFromUser();

        //print out Rental Agreement
        System.out.println("Thank you! Please wait while the Rental Agreement is generated...\n");
        Checkout tool_rental = new Checkout(tool_code,num_rental_days,discount_percent,check_out_date);
        TimeUnit.SECONDS.sleep(2);
        tool_rental.printRentalAgreement();

        //Final Statement
        System.out.println("\nPlease enjoy your " + tool_type + " for " + num_rental_days + " days.\n" +
                "We will see you on " + tool_rental.due_date.format(DateTimeFormatter.ofPattern("MM/dd/yy")) + " for your return.");
    }


    /**
     * Method to ask the user to pick a tool code from the provided list
     * @param availableTools dictionary of available tool codes
     * @return String the tool code selected
     */
    private static String getToolCodeFromUser(String[][] availableTools){
        String toolCode;
        Scanner kb = new Scanner(System.in);
        boolean isValid = false;

        System.out.println("""

                Welcome to the Hardware Store Demo!
                Please enter the Tool Code of the item that the customer wants to rent
                """);
        for (Object[] row:availableTools) {
            System.out.format("%15s%15s%15s%n", row);
        }

        do{
            toolCode = kb.nextLine();
            for (int i = 2;i <=5;i++){
                if(toolCode.equalsIgnoreCase(availableTools[i][0])){
                    isValid = true;
                    break;
                }
            }
            if(!isValid){
                System.out.println("That is an invalid tool code. Please enter a different tool code.");
            }
        }while (!isValid);

        return toolCode;
    }


    /**
     * Method to get the tool type after the user has given the tool code
     * @param toolCode Tool Code for the tool being rented
     * @param availableTools dictionary of available tool codes
     * @return String the tool type selected
     */
    private static String getToolType(String toolCode, String[][] availableTools){
        String toolType = "";
        for (int i = 2;i <=5;i++){
            if(toolCode.equalsIgnoreCase(availableTools[i][0])){
                toolType = availableTools[i][1];
                break;
            }
        }

        return toolType;
    }


    /**
     * Method to ask the user how many days the tool will be rented
     * @param toolType The tool type is used in the question towards the user
     * @return int the number of days the tool will be rented
     */
    private static int getNumberOfRentalDaysFromUser(String toolType){
        int numRentalDays = 0;
        Scanner kb = new Scanner(System.in);
        boolean isValid = false;

        System.out.println("How many days is the " + toolType + " being rented? Please enter whole numbers only.");
        do{
            if(kb.hasNextInt()) {
                numRentalDays = kb.nextInt();
                if(numRentalDays > 0){
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

        return numRentalDays;
    }


    /**
     * Method to ask the user for the Checkout Date
     * @return LocalDate checkout date
     */
    protected static LocalDate getDateFromUser(){
        String date;
        LocalDate checkOutDate = null;
        Scanner kb = new Scanner(System.in);
        boolean isValid = false;

        System.out.println("Please enter today's date in the following format: MM/dd/yyyy");
        do{
            date = kb.nextLine();
            try{
                checkOutDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                isValid = true;
            }catch (DateTimeParseException E){
                System.out.println("This is not in the correct date format. Please enter the date as MM/dd/yyyy.");
                //kb.nextLine();
            }
        }while(!isValid);

        return  checkOutDate;
    }


    /**
     * Method to ask the user for the discount amount given to the customer
     * @return int the discount percent given to the customer e.g. 20%
     */
    private static int getDiscountPercentFromUser(){
        int discountPercent = 0;
        Scanner kb = new Scanner(System.in);
        boolean isValid = false;

        System.out.println("Please enter the amount of discount that will be applied to this order as a whole number from 0-100 (e.g. 20 = 20%)");
        do{
            if(kb.hasNextInt()) {
                discountPercent = kb.nextInt();
                if((discountPercent >= 0 && discountPercent <= 100)){
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

        return discountPercent;
    }
}