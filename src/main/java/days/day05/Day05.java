package days.day05;

import days.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day05 {
    public int binaryBoarding(String[] input) {

        List<Integer> seatIds = new ArrayList<>();

        for (String boardingPass : input) {
            int rowsOnThePlane = 127;
            int columnsOfSeats = 7;

            List<Integer> rows = new ArrayList<>();
            for (int i = 0; i <= rowsOnThePlane; i++) {
                rows.add(i);
            }

            List<Integer> columns = new ArrayList<>();
            for (int i = 0; i <= columnsOfSeats; i++) {
                columns.add(i);
            }

            int k = 0;
            while (rowsOnThePlane != 0) {
                if (boardingPass.charAt(k) == 'F') {
                    rows = rows.subList(0, (rowsOnThePlane + 1) / 2);
                } else if (boardingPass.charAt(k) == 'B') {
                    rows = rows.subList((rowsOnThePlane + 1) / 2, rows.size());
                }
                rowsOnThePlane /= 2;
                k++;
            }
            while (columnsOfSeats != 0) {
                if (boardingPass.charAt(k) == 'L') {
                    columns = columns.subList(0, (columnsOfSeats + 1) / 2);
                } else if (boardingPass.charAt(k) == 'R') {
                    columns = columns.subList((columnsOfSeats + 1) / 2, columns.size());
                }
                columnsOfSeats /= 2;
                k++;
            }
            seatIds.add(rows.get(0) * 8 + columns.get(0));
        }

        int highest = seatIds.get(0);
        for (int l = 1; l < input.length; l++) {
            if (seatIds.get(l) > highest) {
                highest = seatIds.get(l);
            }
        }

        // PART 2: find the missing boarding pass in the list
        for (int m = 84; m <= 890; m ++) {
            if (!seatIds.contains(m)) {
                System.out.println(m);
            }
        }

        return highest;
    }

    public static void main(String[] args) throws IOException {
        String[] input = Day.loadInput("day05");
        Day05 day05 = new Day05();
        System.out.println(day05.binaryBoarding(input));
    }
}
