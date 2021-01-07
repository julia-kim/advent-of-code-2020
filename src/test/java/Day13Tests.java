import days.day13.Day13;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day13Tests {
    Day13 day13 = new Day13();

    @Test
    public void testPart01() {
        String[] input = {"939", "7,13,x,x,59,x,31,19"};
        assertEquals(295, day13.part01(input));
    }

    @Test
    public void testPart02() {
        String[] input = {"", "7,13,x,x,59,x,31,19"};
        assertEquals(1068781, day13.part02(input));

        input = new String[]{"", "17,x,13,19"};
        assertEquals(3417, day13.part02(input));

        input = new String[]{"", "67,x,7,59,61"};
        assertEquals(779210, day13.part02(input));

        input = new String[]{"", "1789,37,47,1889"};
        assertEquals(1202161486, day13.part02(input));
    }
}
