/**
 * This class creates an instance of a tool for a hardware store. Details about the tool include the following:
 * Tool Code, Tool Type, Tool Brand, Daily Rental Charge, if there is a weekday charge, if there is a weekend charge, if there is a holiday charge
 *
 * @author Ty Bird
 * @since 2023-07-11
 */
@SuppressWarnings("FieldCanBeLocal")
public class Tools {
    protected String tool_code;
    protected String tool_type;
    protected String tool_brand;
    protected double daily_rental_charge;
    protected boolean weekday_charge;
    protected boolean weekend_charge;
    protected boolean holiday_charge;

    //set finals for easy future changes
    private final String LADDER_DAILY_CHARGE = "1.99";
    private final String LADDER_WEEKDAY_CHARGE = "true";
    private final String LADDER_WEEKEND_CHARGE = "true";
    private final String LADDER_HOLIDAY_CHARGE = "false";
    private final String CHAINSAW_DAILY_CHARGE = "1.49";
    private final String CHAINSAW_WEEKDAY_CHARGE = "true";
    private final String CHAINSAW_WEEKEND_CHARGE = "false";
    private final String CHAINSAW_HOLIDAY_CHARGE = "true";
    private final String JACKHAMMER_DAILY_CHARGE = "2.99";
    private final String JACKHAMMER_WEEKDAY_CHARGE = "true";
    private final String JACKHAMMER_WEEKEND_CHARGE = "false";
    private final String JACKHAMMER_HOLIDAY_CHARGE = "false";

    String[][] available_tools = {
           // {"Tool Code","Tool Type","Brand","Daily Charge Rate","If Weekday Charge","If Weekend Charge","If Holiday Charge",},
            {"CHNS","Chainsaw","Stihl",CHAINSAW_DAILY_CHARGE,CHAINSAW_WEEKDAY_CHARGE,CHAINSAW_WEEKEND_CHARGE,CHAINSAW_HOLIDAY_CHARGE},
            {"LADW","Ladder","Werner",LADDER_DAILY_CHARGE,LADDER_WEEKDAY_CHARGE,LADDER_WEEKEND_CHARGE,LADDER_HOLIDAY_CHARGE},
            {"JAKD","Jackhammer","DeWalt",JACKHAMMER_DAILY_CHARGE,JACKHAMMER_WEEKDAY_CHARGE,JACKHAMMER_WEEKEND_CHARGE,JACKHAMMER_HOLIDAY_CHARGE},
            {"JAKR","Jackhammer","Ridgid",JACKHAMMER_DAILY_CHARGE,JACKHAMMER_WEEKDAY_CHARGE,JACKHAMMER_WEEKEND_CHARGE,JACKHAMMER_HOLIDAY_CHARGE}};

    /**
     * Constructor for a tool rental
     * Uses the tool code to populate the rest of the tools data
     *
     * @param toolCode Unique identifier to identify a tools type and brand
     * @throws IllegalArgumentException Throws an exception if a tool code doesn't match any in the current list
     */
    protected Tools(String toolCode) throws IllegalArgumentException {

        //set up an array and search the array
        for (String[] toolDetails: available_tools) {
            if (toolDetails[0].equalsIgnoreCase(toolCode)){
                tool_code = toolDetails[0];
                tool_type = toolDetails[1];
                tool_brand = toolDetails[2];
                daily_rental_charge = Double.parseDouble(toolDetails[3]);
                weekday_charge = Boolean.parseBoolean(toolDetails[4]);
                weekend_charge = Boolean.parseBoolean(toolDetails[5]);
                holiday_charge = Boolean.parseBoolean(toolDetails[6]);
                break;
            }
        }
        if (tool_code == null){
            throw new IllegalArgumentException("Invalid Tool Code");
        }
    }
}
