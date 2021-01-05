package days.day12;

import days.Day;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day12 {
    public int part01(String[] input) {
        String direction = "E"; // the ship starts by facing east
        int northSouth = 0;
        int eastWest = 0;
        int manhattanDistance;

        Map<String, Integer> compass = new HashMap();
        compass.put("N", 360);
        compass.put("E", 90);
        compass.put("S", 180);
        compass.put("W", 270);

        for (String action : input) {
            String[] st = action.split("(?<=^\\D)");

            if (st[0].equals("F")) {
                st[0] = direction;
            }

            if (st[0].equals("L") || st[0].equals("R")) {
                int angle = compass.get(direction);

                if (st[0].equals("L")) {
                    angle -= Integer.parseInt(st[1]);
                } else {
                    angle += Integer.parseInt(st[1]);
                }

                if (angle <= 0 || angle > 360) {
                    angle += angle > 360 ? -360 : 360;
                }

                for (Map.Entry<String, Integer> entry : compass.entrySet()) {
                    if (angle == entry.getValue()) {
                        direction = entry.getKey();
                    }
                }
            }

            switch (st[0]) {
                case "N":
                    northSouth += Integer.parseInt(st[1]);
                    break;
                case "S":
                    northSouth -= Integer.parseInt(st[1]);
                    break;
                case "E":
                    eastWest += Integer.parseInt(st[1]);
                    break;
                case "W":
                    eastWest -= Integer.parseInt(st[1]);
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
