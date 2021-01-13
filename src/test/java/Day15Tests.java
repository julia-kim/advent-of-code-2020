import days.day15.Day15;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day15Tests {
    private Day15 day15;
    private String[] input;

    @Before
    public void setup() {
        day15 = new Day15();
        input = new String[]{"3,1,2"};
    }

    @Test
    public void testPart01() {
        assertEquals(1836, day15.play(input, 2020));
    }

    @Test
    public void testPart02() {
        assertEquals(362, day15.play(input, 30000000));
    }
}
