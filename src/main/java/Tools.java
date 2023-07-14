/**
 * This class creates an instance of a tool for a hardware store. Details about the tool include the following:
 * Tool Code, Tool Type, Tool Brand, Daily Rental Charge, if there is a weekday charge, if there is a weekend charge, if there is a holiday charge
 *
 * @author Ty Bird
 * @since 2023-07-11
 */
@SuppressWarnings("FieldCanBeLocal")
public class Tools {
    public String tool_code;
    public String tool_type;
    public String tool_brand;
    public double daily_rental_charge;
    public boolean weekday_charge;
    public boolean weekend_charge;
    public boolean holiday_charge;

    //set finals for easy future changes
    private final double LADDER_DAILY_CHARGE = 1.99;
    private final boolean LADDER_WEEKDAY_CHARGE = true;
    private final boolean LADDER_WEEKEND_CHARGE = true;
    private final boolean LADDER_HOLIDAY_CHARGE = false;
    private final double CHAINSAW_DAILY_CHARGE = 1.49;
    private final boolean CHAINSAW_WEEKDAY_CHARGE = true;
    private final boolean CHAINSAW_WEEKEND_CHARGE = false;
    private final boolean CHAINSAW_HOLIDAY_CHARGE = true;
    private final double JACKHAMMER_DAILY_CHARGE = 2.99;
    private final boolean JACKHAMMER_WEEKDAY_CHARGE = true;
    private final boolean JACKHAMMER_WEEKEND_CHARGE = false;
    private final boolean JACKHAMMER_HOLIDAY_CHARGE = false;


    public Tools(String toolCode) throws IllegalArgumentException {
        if (toolCode.equalsIgnoreCase("CHNS")){
            tool_code = "CHNS";
            tool_type = "Chainsaw";
            tool_brand = "Stihl";
            daily_rental_charge = CHAINSAW_DAILY_CHARGE;
            weekday_charge = CHAINSAW_WEEKDAY_CHARGE;
            weekend_charge = CHAINSAW_WEEKEND_CHARGE;
            holiday_charge = CHAINSAW_HOLIDAY_CHARGE;
        } else if(toolCode.equalsIgnoreCase("LADW")){
            tool_code = "LADW";
            tool_type = "Ladder";
            tool_brand = "Werner";
            daily_rental_charge = LADDER_DAILY_CHARGE;
            weekday_charge = LADDER_WEEKDAY_CHARGE;
            weekend_charge = LADDER_WEEKEND_CHARGE;
            holiday_charge = LADDER_HOLIDAY_CHARGE;
        } else if(toolCode.equalsIgnoreCase("JAKD")){
            tool_code = "JAKD";
            tool_type = "Jackhammer";
            tool_brand = "DeWalt";
            daily_rental_charge = JACKHAMMER_DAILY_CHARGE;
            weekday_charge = JACKHAMMER_WEEKDAY_CHARGE;
            weekend_charge = JACKHAMMER_WEEKEND_CHARGE;
            holiday_charge = JACKHAMMER_HOLIDAY_CHARGE;
        } else if (toolCode.equalsIgnoreCase("JAKR")){
            tool_code = "JAKR";
            tool_type = "Jackhammer";
            tool_brand = "Ridgid";
            daily_rental_charge = JACKHAMMER_DAILY_CHARGE;
            weekday_charge = JACKHAMMER_WEEKDAY_CHARGE;
            weekend_charge = JACKHAMMER_WEEKEND_CHARGE;
            holiday_charge = JACKHAMMER_HOLIDAY_CHARGE;
        } else{
            throw new IllegalArgumentException("Invalid Tool Code");
        }
    }
}
