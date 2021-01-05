import days.day12.Day12;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day12Tests {
    Day12 day12 = new Day12();
    String[] input = {"F10", "N3", "F7", "R90", "F11" };

    @Test
    public void testPart01() {
        assertEquals(25, day12.part01(input));
    }

    @Test
    public void testPart02() {
        assertEquals(286, day12.part02(input));
    }
}
