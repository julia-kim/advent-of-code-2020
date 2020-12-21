package days.day09;

import days.Day;

import java.io.IOException;
import java.util.*;

public class Day09 {
    public Long part01(String[] input) {
        // convert String[] to List<Long>
        ArrayList<Long> data = new ArrayList<>();
        for (String line : input) {
            data.add(Long.valueOf(line));
        }

        int preambleLength = 25;
        Long nextNumber = 0L;
        List<Long> preamble = data.subList(0, preambleLength);
        int counter = preambleLength;
        for (int i = preambleLength; i < data.size(); i++) {
            if (counter != i) {
                return nextNumber;
            }
            nextNumber = data.get(i);
            HashMap<Long, Integer> twoSum = new HashMap<>();
            for (int j = 0; j < preamble.size(); j++) {
                twoSum.put(preamble.get(j), j);
            }

            for (int k = 0; k < preamble.size(); k++) {
                if (twoSum.containsKey(nextNumber - preamble.get(k))) {
                    preamble.remove(0);
                    preamble.add(nextNumber);
                    counter++;
                    break;
                }
            }
        }
        return nextNumber;
    }

    public Long part02(String[] input) {
        long[] data = Arrays.stream(input).mapToLong(Long::parseLong).toArray();
        Long encryptionWeakness = 0L;
        Long invalidNumber = 70639851L; // from part01
        Long sum = 0L;
        HashMap<Long, Integer> sumIndexMap = new HashMap<>();
        sumIndexMap.put(0L, -1);
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
            if (sumIndexMap.containsKey(sum - invalidNumber)) {
                System.out.println(sumIndexMap.get(sum - invalidNumber));
                long[] subArray = Arrays.copyOfRange(data, sumIndexMap.get(sum - invalidNumber) + 1, i);
                long min = Arrays.stream(subArray).min().getAsLong();
                long max = Arrays.stream(subArray).max().getAsLong();
                encryptionWeakness = min + max;
                break;
            }
            sumIndexMap.put(sum, i);
        }

        return encryptionWeakness;
    }

    public static void main(String[] args) throws IOException {
        String[] input = Day.loadInput("day09");
        Day09 day09 = new Day09();
        System.out.println(day09.part01(input));
        System.out.println(day09.part02(input));
    }
}
