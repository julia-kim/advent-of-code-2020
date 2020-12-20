package days.day08;

import days.Day;

import java.io.IOException;
import java.util.LinkedHashMap;

public class Day08 {
    public int part01(String[] input) {
        int accumulator = 0;
        LinkedHashMap<Integer, String> tracker = new LinkedHashMap<>();
        int i = 0;

        while (i < input.length) {
            String[] instruction = input[i].split(" ");
            String operation = instruction[0];
            int argument = Integer.parseInt(instruction[1]);

            if (tracker.containsKey(i)) {
                break;
            }
            tracker.put(i, input[i]); // keep track of instructions already executed


            if (operation.equals("acc")) {
                accumulator += argument;
                i++;
            } else if (operation.equals("jmp")) {
                i += argument;
            } else if (operation.equals("nop")) {
                i++;
            }
        }

        return accumulator;
    }
//
//    public int part02() {
//        return 1;
//    }

    public static void main(String[] args) throws IOException {
        String[] input = Day.loadInput("day08");
        Day08 day08 = new Day08();
        System.out.println(day08.part01(input));
        //System.out.println(day08.part02(input));
    }
}
