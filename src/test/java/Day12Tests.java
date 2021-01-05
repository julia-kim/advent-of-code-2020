import days.day12.Day12;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day12Tests {
    Day12 day12 = new Day12();

    @Test
    public void getManhattanDistance() {
        String[] input = {"F10", "N3", "F7", "R90", "F11"};
        assertEquals(25, day12.part01(input));
    }
}
