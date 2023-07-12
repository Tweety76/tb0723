public class Tools {
    public String tool_code;
    public String tool_type;
    public String tool_brand;
    public double daily_rental_charge;  //daily charge for renting the item
    public boolean weekday_charge;
    public boolean weekend_charge;
    public boolean holiday_charge;

    //set finals for easy future changes
    private final double LADDER_DAILY_CHARGE = 1.99;
    private final double CHAINSAW_DAILY_CHARGE = 1.49;
    private final double JACKHAMMER_DAILY_CHARGE = 2.99;
    private final boolean LADDER_WEEKDAY_CHARGE = true;
    private final boolean CHAINSAW_WEEKDAY_CHARGE = true;
    private final boolean JACKHAMMER_WEEKDAY_CHARGE = true;
    private final boolean LADDER_WEEKEND_CHARGE = true;
    private final boolean CHAINSAW_WEEKEND_CHARGE = false;
    private final boolean JACKHAMMER_WEEKEND_CHARGE = false;
    private final boolean LADDER_HOLIDAY_CHARGE = false;
    private final boolean CHAINSAW_HOLIDAY_CHARGE = true;
    private final boolean JACKHAMMER_HOLIDAY_CHARGE = false;

    /**
     *
     * @param toolCode
     * @throws Exception
     */
    public Tools(String toolCode) throws Exception {
        if (toolCode.equalsIgnoreCase("CHNS")){
            tool_code = toolCode;
            tool_type = "Chainsaw";
            tool_brand = "Stihl";
            daily_rental_charge = LADDER_DAILY_CHARGE;
            weekday_charge = CHAINSAW_WEEKDAY_CHARGE;
            weekend_charge = CHAINSAW_WEEKEND_CHARGE;
            holiday_charge = CHAINSAW_HOLIDAY_CHARGE;
        } else if(toolCode.equalsIgnoreCase("LADW")){
            tool_code = toolCode;
            tool_type = "Ladder";
            tool_brand = "Werner";
            daily_rental_charge = CHAINSAW_DAILY_CHARGE;
            weekday_charge = LADDER_WEEKDAY_CHARGE;
            weekend_charge = LADDER_WEEKEND_CHARGE;
            holiday_charge = LADDER_HOLIDAY_CHARGE;
        } else if(toolCode.equalsIgnoreCase("JAKD")){
            tool_code = toolCode;
            tool_type = "Jackhammer";
            tool_brand = "DeWalt";
            daily_rental_charge = JACKHAMMER_DAILY_CHARGE;
            weekday_charge = JACKHAMMER_WEEKDAY_CHARGE;
            weekend_charge = JACKHAMMER_WEEKEND_CHARGE;
            holiday_charge = JACKHAMMER_HOLIDAY_CHARGE;
        } else if (toolCode.equalsIgnoreCase("JAKR")){
            tool_code = toolCode;
            tool_type = "Jackhammer";
            tool_brand = "Ridgid";
            daily_rental_charge = JACKHAMMER_DAILY_CHARGE;
            weekday_charge = JACKHAMMER_WEEKDAY_CHARGE;
            weekend_charge = JACKHAMMER_WEEKEND_CHARGE;
            holiday_charge = JACKHAMMER_HOLIDAY_CHARGE;
        } else{
            throw new Exception("Invalid Tool Code");
        }
    }

    //will probably remove the below code
    /*public void setTool_code(String toolCode){
        tool_code = toolCode;
    }

    public String getTool_code(){
        return tool_code;
    }

    public void setTool_type(String toolType){
        tool_type = toolType;
    }

    public String getTool_type(){
        return tool_type;
    }*/
}
