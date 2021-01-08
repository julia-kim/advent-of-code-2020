import days.day11.Day11;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class Day11Tests {
    Day11 day11 = new Day11();
    String[] input = {
            "L.LL.LL.LL",
            "LLLLLLL.LL",
            "L.L.L..L..",
            "LLLL.LL.LL",
            "L.LL.LL.LL",
            "L.LLLLL.LL",
            "..L.L.....",
            "LLLLLLLLLL",
            "L.LLLLLL.L",
            "L.LLLLL.LL",
    };

    @Test
    public void testPart01() {
        assertEquals(37, day11.solveSeatingSystem(input, true));
    }

    @Test
    public void testPart02() {
        assertEquals(26, day11.solveSeatingSystem(input, false));
    }

    @Test
    public void findFirstSeats() {
        // empty seat should see eight occupied seats
        input = new String[]{
                ".......#.",
                "...#.....",
                ".#.......",
                ".........",
                "..#L....#",
                "....#....",
                ".........",
                "#........",
                "...#....."};
        char[][] layoutV1 = day11.getSeatingArrangementFromInput(input);
        assertEquals(Arrays.asList('#', '#', '#', '#', '#', '#', '#', '#'), day11.getFirstVisibleSeats(layoutV1, 4, 3));

        // leftmost empty seat should only see one empty seat
        input = new String[]{
                ".............",
                ".L.L.#.#.#.#.",
                ".............",
        };
        char[][] layoutV2 = day11.getSeatingArrangementFromInput(input);
        assertEquals(Arrays.asList('L'), day11.getFirstVisibleSeats(layoutV2, 1, 1));
    }
}
