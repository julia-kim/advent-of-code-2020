package days.day08;

import days.Day;

import java.io.IOException;
import java.util.HashMap;
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

    public int part02(String[] input) {
        int accumulator = 0;
        LinkedHashMap<Integer, String> tracker = new LinkedHashMap<>();
        HashMap<String, String> operationMap = new HashMap<>();
        operationMap.put("nop", "jmp");
        operationMap.put("jmp", "nop");

        boolean gameIsBroken = true;
        while (gameIsBroken) {
            accumulator = 0;
            LinkedHashMap<Integer, String> infiniteLoopTracker = new LinkedHashMap<>();
            for (int j = 0; j < input.length; j++) {
                if (tracker.containsKey(j)) {
                    input[j] = tracker.get(j);
                } else if (operationMap.containsKey(input[j].substring(0, 3))) {
                    tracker.put(j, input[j]);
                    input[j] = input[j].replace(input[j].substring(0, 3), operationMap.get(input[j].substring(0, 3)));
                    break;
                }
            }

            int i = 0;
            while (i < input.length) {
                String[] instruction = input[i].split(" ");
                String operation = instruction[0];
                int argument = Integer.parseInt(instruction[1]);

                if (infiniteLoopTracker.containsKey(i)) {
                    break;
                }

                infiniteLoopTracker.put(i, input[i]);

                if (infiniteLoopTracker.containsKey(input.length - 1)) {
                    gameIsBroken = false;
                }

                switch (operation) {
                    case "acc":
                        accumulator += argument;
                        i++;
                        break;
                    case "jmp":
                        i += argument;
                        break;
                    case "nop":
                        i++;
                        break;
                }
            }
        }
        return accumulator;
    }

    public static void main(String[] args) throws IOException {
        String[] input = Day.loadInput("day08");
        Day08 day08 = new Day08();
        System.out.println(day08.part01(input));
        System.out.println(day08.part02(input));
    }
}
