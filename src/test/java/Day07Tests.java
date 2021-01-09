import days.day07.Day07;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day07Tests {
    private Day07 day07;
    private String[] input;

    @Before
    public void init() {
        input = new String[]{
                "light red bags contain 1 bright white bag, 2 muted yellow bags.",
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                "bright white bags contain 1 shiny gold bag.",
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
                "faded blue bags contain no other bags.",
                "dotted black bags contain no other bags."
        };

       day07 = new Day07(input);
    }

    @Test
    public void testPart01() {
        assertEquals(4, day07.part01());
    }

    @Test
    public void testPart02() {
        assertEquals(32, day07.part02());
    }

}
