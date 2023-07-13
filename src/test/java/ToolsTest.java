import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToolsTest {

    @Test
    void toolCodeTestCHNS() throws IllegalArgumentException{
        var tool = new Tools("CHNS");
        assertEquals("CHNS",tool.tool_code);
        assertEquals("Chainsaw", tool.tool_type);
        assertEquals("Stihl", tool.tool_brand);
        assertEquals(1.49, tool.daily_rental_charge);
        assertTrue(tool.weekday_charge);
        assertFalse(tool.weekend_charge);
        assertTrue(tool.holiday_charge);
    }

    @Test
    void toolCodeTestLADW() throws IllegalArgumentException{
        var tool = new Tools("LADW");
        assertEquals("LADW",tool.tool_code);
        assertEquals("Ladder", tool.tool_type);
        assertEquals("Werner", tool.tool_brand);
        assertEquals(1.99, tool.daily_rental_charge);
        assertTrue(tool.weekday_charge);
        assertTrue(tool.weekend_charge);
        assertFalse(tool.holiday_charge);
    }

    @Test
    void toolCodeTestJAKD() throws IllegalArgumentException{
        var tool = new Tools("JAKD");
        assertEquals("JAKD",tool.tool_code);
        assertEquals("Jackhammer", tool.tool_type);
        assertEquals("DeWalt", tool.tool_brand);
        assertEquals(2.99, tool.daily_rental_charge);
        assertTrue(tool.weekday_charge);
        assertFalse(tool.weekend_charge);
        assertFalse(tool.holiday_charge);
    }

    @Test
    void toolCodeTestJAKR() throws IllegalArgumentException{
        var tool = new Tools("JAKR");
        assertEquals("JAKR",tool.tool_code);
        assertEquals("Jackhammer", tool.tool_type);
        assertEquals("Ridgid", tool.tool_brand);
        assertEquals(2.99, tool.daily_rental_charge);
        assertTrue(tool.weekday_charge);
        assertFalse(tool.weekend_charge);
        assertFalse(tool.holiday_charge);
    }

    @Test
    void toolCodeTestCHNSIgnoreCase() throws IllegalArgumentException{
        var tool = new Tools("ChNS");
        assertEquals("CHNS",tool.tool_code);
        assertEquals("Chainsaw", tool.tool_type);
        assertEquals("Stihl", tool.tool_brand);
        assertEquals(1.49, tool.daily_rental_charge);
        assertTrue(tool.weekday_charge);
        assertFalse(tool.weekend_charge);
        assertTrue(tool.holiday_charge);
    }

    @Test
    void toolCodeTestLADWIgnoreCase() throws IllegalArgumentException{
        var tool = new Tools("laDW");
        assertEquals("LADW",tool.tool_code);
        assertEquals("Ladder", tool.tool_type);
        assertEquals("Werner", tool.tool_brand);
        assertEquals(1.99, tool.daily_rental_charge);
        assertTrue(tool.weekday_charge);
        assertTrue(tool.weekend_charge);
        assertFalse(tool.holiday_charge);
    }

    @Test
    void toolCodeTestJAKDIgnoreCase() throws IllegalArgumentException{
        var tool = new Tools("JakD");
        assertEquals("JAKD",tool.tool_code);
        assertEquals("Jackhammer", tool.tool_type);
        assertEquals("DeWalt", tool.tool_brand);
        assertEquals(2.99, tool.daily_rental_charge);
        assertTrue(tool.weekday_charge);
        assertFalse(tool.weekend_charge);
        assertFalse(tool.holiday_charge);
    }

    @Test
    void toolCodeTestJAKRIgnoreCase() throws IllegalArgumentException{
        var tool = new Tools("jakr");
        assertEquals("JAKR",tool.tool_code);
        assertEquals("Jackhammer", tool.tool_type);
        assertEquals("Ridgid", tool.tool_brand);
        assertEquals(2.99, tool.daily_rental_charge);
        assertTrue(tool.weekday_charge);
        assertFalse(tool.weekend_charge);
        assertFalse(tool.holiday_charge);
    }

    @Test
    void invalidToolCodeCHNS(){
        assertThrows(IllegalArgumentException.class, () -> new Tools("CHS"));
    }

    @Test
    void invalidToolCodeLADW(){
        assertThrows(IllegalArgumentException.class, () -> new Tools("LAD"));
    }

    @Test
    void invalidToolCodeJAKD(){
        assertThrows(IllegalArgumentException.class, () -> new Tools("JADK"));
    }

    @Test
    void invalidToolCodeJAKR(){
        assertThrows(IllegalArgumentException.class, () -> new Tools("JARK"));
    }

    @Test
    void invalidToolCodeExtraSpaceAfter(){
        assertThrows(IllegalArgumentException.class, () -> new Tools("JAKR "));
    }

    @Test
    void invalidToolCodeExtraSpaceBefore(){
        assertThrows(IllegalArgumentException.class, () -> new Tools(" JAKR"));
    }

    @Test
    void invalidToolCodeEmpty(){
        assertThrows(IllegalArgumentException.class, () -> new Tools(""));
    }
}