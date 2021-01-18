import days.day16.Day16;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day16Tests {
    Day16 day16;
    String[] input;

    @Test
    public void testPart01() {
        input = new String[]{
                "class: 1-3 or 5-7",
                "row: 6-11 or 33-44",
                "seat: 13-40 or 45-50",
                "",
                "your ticket:",
                "7,1,14",
                "",
                "nearby tickets:",
                "7,3,47",
                "40,4,50",
                "55,2,20",
                "38,6,12"};
        day16 = new Day16(input);
        assertEquals(71, day16.part01());
    }
}
