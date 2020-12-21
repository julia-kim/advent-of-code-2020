package days.day10;

import days.Day;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class Day10 {
    public int part01(String[] input) {
        int[] data = getSortedData(input);
        int oneCount = 0;
        int threeCount = 0;
        LinkedHashMap<Integer, Integer> adapterChain = new LinkedHashMap<>();
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] - data[i] <= 3 && !adapterChain.containsKey(data[i])) {
                    adapterChain.put(data[i], data[j] - data[i]);
                }
            }
        }
        for (Integer key : adapterChain.keySet()) {
            if (adapterChain.get(key).equals(1)) {
                oneCount++;
            }
            if (adapterChain.get(key).equals(3)) {
                threeCount++;
            }
        }
        return oneCount * threeCount;
    }

//    public int part02(String[] input) {
//
//    }

    public int[] getSortedData(String[] input) {
        int[] data = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
        int newData[] = new int[data.length + 2];
        for (int k = 0; k < data.length; k++) {
            newData[k] = data[k];
        }
        newData[data.length] = 0; // charging outlet has an effective joltage rating of 0
        int max = Arrays.stream(data).max().getAsInt();
        newData[data.length + 1] = max + 3; // device's built-in adapter is always 3 higher than the highest adapter

        for (int i = 0; i < newData.length; i++) {
            for (int j = i + 1; j < newData.length; j++) {
                if (newData[i] > newData[j]) {
                    int temp = newData[i];
                    newData[i] = newData[j];
                    newData[j] = temp;
                }
            }
        }
        return newData;
    }

    public static void main(String[] args) throws IOException {
        String[] input = Day.loadInput("day10");
        Day10 day10 = new Day10();
        System.out.println(day10.part01(input));
        // System.out.println(day10.part02(input));
    }
}
