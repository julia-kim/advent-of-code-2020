import days.day10.Day10;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day10Tests {
    Day10 day10 = new Day10();
    String[] ex1 = {"16", "10", "15", "5", "1", "11", "7", "19", "6", "12", "4"};
    String[] ex2 = {"28", "33", "18", "42", "31", "14", "46", "20", "48", "47", "24", "23", "49", "45", "19", "38", "39", "11", "1", "32", "25", "35", "8", "17", "7", "9", "4", "2", "34", "10", "3"};

    @Test
    public void testPart01() {
        assertEquals(35, day10.part01(ex1));
        assertEquals(220, day10.part01(ex2));
    }

    @Test
    public void testPart02() {
        assertEquals(8, day10.part02(ex1));
        assertEquals(19208, day10.part02(ex2));
    }

}
