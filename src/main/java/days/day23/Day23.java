package days.day23;

import days.Day;

import java.util.ArrayList;
import java.util.List;

public class Day23 {
    public Object part01(String[] input) {
        CircularLinkedList cups = new CircularLinkedList();
        for (char c : input[0].toCharArray()) {
            cups.add(Character.getNumericValue(c));
        }

        int currentCup = cups.getFirst();
        int moves = 0;
        while (moves < 100) {
            int destination = currentCup - 1;
            List<Integer> pickedUpCups = new ArrayList<>();

            int curr = currentCup;
            int i = 0;
            while (i < 3) { // pick up 3
                Node pickedUp = cups.removeNext(curr);
                pickedUpCups.add(pickedUp.data);
                i++;
            }

            boolean found = false;
            while (!found) {
                if (destination <= 0) {
                    destination = 9;
                }
                if (cups.contains(destination)) {
                    found = true;
                    int dest = destination;
                    for (Integer cup : pickedUpCups) {
                        cups.addAfter(dest, cup);
                        dest = cup;
                    }
                } else {
                    destination--;
                }
            }

            currentCup = cups.getNext(currentCup);
            moves++;
        }
        return cups;
    }

//    public Object part02(String[] input) {
//        return -1;
//    }

    public static void main(String[] args) {
        String[] input = Day.loadInput("day23");
        Day23 day23 = new Day23();
        System.out.println("part one: " + day23.part01(input));
        // System.out.println("part two: " + day23.part02(input));
    }
}
