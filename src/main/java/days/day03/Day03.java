package days.day03;

import java.io.*;
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
        InputStream input = Day03.class.getClassLoader().getResourceAsStream("day03.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(input));
        try {
            ArrayList<String> arrList = new ArrayList<>();
            String line;

            while ((line = in.readLine()) != null) {
                String st = line;
                arrList.add(st);
            }
            String[] arr = arrList.toArray(new String[arrList.size()]);

            Day03 day03 = new Day03();
            System.out.println(day03.part01(arr, 3, 1));
            System.out.println(day03.part02(arr));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
