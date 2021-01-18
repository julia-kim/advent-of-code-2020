import days.day22.Day22;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day22Tests {
    Day22 day22;
    String[] input;

    @Before
    public void setup() {
        day22 = new Day22();
        input = new String[]{
                "Player 1:",
                "9",
                "2",
                "6",
                "3",
                "1",
                "",
                "Player 2:",
                "5",
                "8",
                "4",
                "7",
                "10"};
    }

    @Test
    public void testPart01() {
        assertEquals(306, day22.part01(input));
    }

    @Test
    public void testPart02() {
        assertEquals(291, day22.part02(input));
    }
}
