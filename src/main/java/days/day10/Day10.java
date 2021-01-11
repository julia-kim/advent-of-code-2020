package days.day10;

import days.Day;

import java.io.IOException;
import java.util.*;

public class Day10 {
    public int part01(String[] input) {
        int[] data = getSortedData(input);
        int oneCount = 0;
        int threeCount = 0;
        Map<Integer, Integer> adapterChain = new LinkedHashMap<>();
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

    public long part02(String[] input) {
        long totalAdapterArrangements;
        int[] data = getSortedData(input);
        List<Integer> adapters = new ArrayList<>();
        for (int i : data) {
            adapters.add(i);
        }

        int joltDiff = 3;
        Map<Integer, Integer> adapterChain = new LinkedHashMap<>();
        while (joltDiff > 0) {
            for (int i = data.length - 1; i >= 0; i--) {
                if (adapters.contains(adapters.get(i) - joltDiff)) {
                    adapterChain.put(data[i], adapterChain.getOrDefault(data[i], 0) + 1);
                }
            }
            joltDiff--;
        }
        System.out.println(adapterChain);

        // recursive method which works for the sample cases, but gives me an OutOfMemoryError for the full input ㅜㅜ
        Set<Set<Integer>> uniqueArrangements = arrange(adapters, adapterChain, adapters.size() - 1, new LinkedHashSet<>());

        totalAdapterArrangements = uniqueArrangements.size();

        return totalAdapterArrangements;
    }

    public Set<Set<Integer>> arrange(List<Integer> input, Map<Integer, Integer> adapterChain, int i, Set<Set<Integer>> result) {
        if (i == 0) {
            return result; // DONE
        }

        if (result.size() == 0) {
            Set<Integer> subList = new LinkedHashSet<>();
            subList.add(input.get(i));
            result.add(subList);

            return arrange(input, adapterChain, i, result);
        }

        Set<Set<Integer>> newResult = new LinkedHashSet<>();
        for (Set<Integer> subList : result) {
            int joltDiff = 1;
            while (joltDiff <= adapterChain.get(input.get(i))) {
                Set<Integer> newSubList = new LinkedHashSet<>();
                newSubList.addAll(subList);
                newSubList.add(input.get(i - joltDiff));
                newResult.add(newSubList);
                joltDiff++;
            }
        }

        return arrange(input, adapterChain, i - 1, newResult);
    }

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
        System.out.println("part one: " + day10.part01(input));
        System.out.println("part two: " + day10.part02(input));
    }
}
