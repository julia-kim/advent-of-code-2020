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

    public int part02(String[] input) {
        Waypoint wp = new Waypoint(10, 1); // the waypoint starts 10 units east and 1 unit north relative to the ship
        int[] shipCoords = {0, 0};
        int manhattanDistance;

        for (String action : input) {
            String[] st = action.split("(?<=^\\D)");
            String move = st[0];
            int units = Integer.parseInt(st[1]);

            switch (move) {
                case "F":
                    // move the ship towards waypoint n times
                    shipCoords[0] += (wp.getX() * units);
                    shipCoords[1] += (wp.getY() * units);
                    break;
                case "N":
                    wp.moveWaypoint(0, units);
                    break;
                case "S":
                    wp.moveWaypoint(0, -units);
                    break;
                case "E":
                    wp.moveWaypoint(units, 0);
                    break;
                case "W":
                    wp.moveWaypoint(-units, 0);
                    break;
                case "L":
                case "R":
                    wp.rotateWaypoint(move, units);
                    break;
            }
        }

        manhattanDistance = Math.abs(shipCoords[0]) + Math.abs(shipCoords[1]);
        return manhattanDistance;
    }

    public static void main(String[] args) throws IOException {
        String[] input = Day.loadInput("day12");
        Day12 day12 = new Day12();
        System.out.println(day12.part01(input));
        System.out.println(day12.part02(input));
    }
}
