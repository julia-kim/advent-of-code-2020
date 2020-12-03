package days.day03;

import days.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day03 {

    public int part01(String[] rowOfTrees, int right, int down) {
        HashMap<Integer, Integer> positions = new HashMap<>();
        int trees = 0;
        int k = 0;

        for (int i = 0; i < rowOfTrees.length; i += down) {
            positions.put(i, k);
            while (k + right >= rowOfTrees[i].length()) {
                k -= rowOfTrees[i].length();
            }
            k += right;
        }

        for (int i = 0; i < rowOfTrees.length; i += down) {
            if (rowOfTrees[i].charAt(positions.get(i)) == '#') {
                trees++;
            }
        }

        return trees;
    }

    // Right 1, down 1.
    // Right 3, down 1.
    // Right 5, down 1.
    // Right 7, down 1.
    // Right 1, down 2.
    public double part02(String[] slopes) {
        Scanner sc = new Scanner(System.in);
        int right = 0;
        int down = 0;
        ArrayList<Integer> treesEncountered = new ArrayList<>();

        do {
            System.out.println("Enter right and down steps:");
            right = sc.nextInt();
            down = sc.nextInt();
            treesEncountered.add(part01(slopes, right, down));
            System.out.println("Press 'c' to continue. Enter 'y' to stop.");
            sc.nextLine();
        } while (!sc.next().equals("y"));

        double multipliedTreesEncountered = treesEncountered.get(0);
        for (int i = 1; i < treesEncountered.size(); i++) {
            multipliedTreesEncountered *= treesEncountered.get(i);
        }
        return multipliedTreesEncountered;
    }

    public static void main(String[] args) throws IOException {
        String[] input = Day.loadInput("day03");
        Day03 day03 = new Day03();
        System.out.println(day03.part01(input, 3, 1));
        System.out.println(day03.part02(input));
    }
}
