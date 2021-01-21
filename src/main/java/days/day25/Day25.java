package days.day25;

import days.Day;

public class Day25 {
    public long part01(String[] input) {
        long encryptionKey = 1;
        final int cardPublicKey = Integer.parseInt(input[0]);
        final int doorPublicKey = Integer.parseInt(input[1]);
        final int subjectNumber = 7;
        final int divisor = 20201227;
        int value = 1;
        int loopSize = 0;

        // Set the value to itself multiplied by the subject number.
        // Set the value to the remainder after dividing the value by 20201227.
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            value *= subjectNumber;
            value %= divisor;

            if (value == cardPublicKey) {
                loopSize = i;
                break;
            }
        }

        for (int i = 1; i <= loopSize; i++) {
            encryptionKey *= doorPublicKey;
            encryptionKey %= divisor;
        }

        return encryptionKey;
    }

    public static void main(String[] args) {
        String[] input = Day.loadInput("day25");
        Day25 day25 = new Day25();
        System.out.println(day25.part01(input));
        // System.out.println(day25.part02(input));
    }
}
