package days.day11;

import days.Day;

import java.util.ArrayList;
import java.util.List;

public class Day11 {
    public int solveSeatingSystem(String[] input, boolean isPartOne) {
        int totalOccupiedSeats = 0;
        char[][] seatingArrangement = getSeatingArrangementFromInput(input);

        boolean chaos = true;
        int previous = 0;
        while (chaos) {
            seatingArrangement = simulate(seatingArrangement, isPartOne);
            totalOccupiedSeats = countOccupied(seatingArrangement);
            if (previous == totalOccupiedSeats) {
                chaos = false;
            }
            previous = totalOccupiedSeats;
        }

        return totalOccupiedSeats;
    }

    public char[][] getSeatingArrangementFromInput(String[] input) {
        // turn input into a 2d array
        char[][] seatingArrangement = new char[input.length][input[0].length()];
        for (int r = 0; r < input.length; r++) {
            char[] positions = input[r].toCharArray();
            System.arraycopy(positions, 0, seatingArrangement[r], 0, seatingArrangement[r].length);
        }
        return seatingArrangement;
    }

    public List<Character> getAdjacentSeats(char[][] arr, int row, int col) {
        int rows = arr.length;
        int columns = arr[row].length;
        List<Character> adjacents = new ArrayList<>();
        for (int j = row - 1; j <= row + 1; j++) {
            for (int i = col - 1; i <= col + 1; i++) {
                if (i >= 0 && j >= 0 && i < columns && j < rows && !(j == row && i == col)) {
                    adjacents.add(arr[j][i]);
                }
            }
        }
        return adjacents;
    }

    public List<Character> getFirstVisibleSeats(char[][] arr, int row, int col) {
        List<Character> result = new ArrayList<>();

        int[][] directions = {
                {0, -1},    // up
                {1, -1},    // up right
                {1, 0},     // right
                {1, 1},     // down right
                {0, 1},     // down
                {-1, 1},    // down left
                {-1, 0},    // left
                {-1, -1},   // left up
        };

        for (int[] direction : directions) {
            int x = row;
            int y = col;

            boolean endReached = false;
            while (!endReached) {
                try {
                    x += direction[0];
                    y += direction[1];

                    boolean isSeat = arr[x][y] == 'L' || arr[x][y] == '#';
                    if (isSeat) {
                        result.add(arr[x][y]);
                        endReached = true;
                    }
                } catch (ArrayIndexOutOfBoundsException ignore) {
                    endReached = true;
                }
            }
        }
        return result;
    }

    public boolean isOccupied(char seat) {
        return seat == '#';
    }

    public char[][] simulate(char[][] seats, boolean isPartOne) {
        /* Rules:
           1) If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
           2) If a seat is occupied (#) and four (five for part two) or more seats adjacent to it are also occupied,
           the seat becomes empty.
           3) Otherwise, the seat's state does not change.
        */

        // clone seating arrangement
        char[][] newArrangement = new char[seats.length][seats[0].length];
        for (int i = 0; i < seats.length; i++) {
            newArrangement[i] = seats[i].clone();
        }

        for (int r = 0; r < seats.length; r++) {
            for (int c = 0; c < seats[r].length; c++) {
                char currentSeat = seats[r][c];
                List<Character> adjacents = isPartOne ? getAdjacentSeats(seats, r, c) : getFirstVisibleSeats(seats, r, c);
                int occupiedSeats = 0;
                for (char seat : adjacents) {
                    if (isOccupied(seat)) {
                        occupiedSeats++;
                    }
                }
                if (currentSeat == 'L' && !adjacents.contains('#')) {
                    newArrangement[r][c] = '#';
                }
                if (isOccupied(currentSeat)) {
                    if (occupiedSeats >= (isPartOne ? 4 : 5)) {
                        newArrangement[r][c] = 'L';
                    }
                }
            }
        }
        return newArrangement;
    }

    public int countOccupied(char[][] seatingArrangement) {
        int occupiedSeats = 0;
        for (int r = 0; r < seatingArrangement.length; r++) {
            for (int c = 0; c < seatingArrangement[r].length; c++) {
                if (isOccupied(seatingArrangement[r][c])) {
                    occupiedSeats++;
                }
            }
        }
        return occupiedSeats;
    }

    public static void main(String[] args) {
        String[] input = Day.loadInput("day11");
        Day11 day11 = new Day11();
        System.out.println("part one: " + day11.solveSeatingSystem(input, true));
        System.out.println("part two: " + day11.solveSeatingSystem(input, false));
    }
}