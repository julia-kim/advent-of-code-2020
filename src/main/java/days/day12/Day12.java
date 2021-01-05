package days.day12;

import days.Day;

import java.io.IOException;

public class Day12 {
    public int part01(String[] input) {
        Direction dir = Direction.E; // the ship starts by facing east
        int northSouth = 0;
        int eastWest = 0;
        int manhattanDistance;

        for (String action : input) {
            String[] st = action.split("(?<=^\\D)");
            String move = st[0];
            int units = Integer.parseInt(st[1]);

            if (move.equals("F")) {
                move = dir.name();
            }

            if (move.equals("L")) {
                dir = dir.toLeft(units);
            } else if (move.equals("R")) {
                dir = dir.toRight(units);
            }

            switch (move) {
                case "N":
                    northSouth += units;
                    break;
                case "S":
                    northSouth -= units;
                    break;
                case "E":
                    eastWest += units;
                    break;
                case "W":
                    eastWest -= units;
                    break;
            }
        }
        manhattanDistance = Math.abs(eastWest) + Math.abs(northSouth);
        return manhattanDistance;
    }

    public static void main(String[] args) throws IOException {
        String[] input = Day.loadInput("day12");
        Day12 day12 = new Day12();
        System.out.println(day12.part01(input));
        // System.out.println(day12.part02());
    }
}
