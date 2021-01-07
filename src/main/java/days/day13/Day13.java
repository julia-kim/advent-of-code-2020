package days.day13;

import days.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day13 {
    public int part01(String[] input) {
        int startTime = Integer.parseInt(input[0]);
        int departTime = startTime;
        int[] busIds = Arrays.stream(input[1].split("(,x?)+")).mapToInt(Integer::parseInt).toArray();
        int earliestBus = 0;

        outer:
        while (departTime > 0) {
            for (int id : busIds) {
                if (departTime % id == 0) {
                    earliestBus = id;
                    break outer;
                }
            }
            departTime++;
        }
        return earliestBus * (departTime - startTime);
    }

    public long part02(String[] input) {
        // 𝕮𝖍𝖎𝖓𝖊𝖘𝖊 𝕽𝖊𝖒𝖆𝖎𝖓𝖉𝖊𝖗 𝕿𝖍𝖊𝖔𝖗𝖊𝖒
        // x ≡ a1 (mod m1)
        // x ≡ a2 (mod m2)
        // x ≡ a3 (mod m3)
        // etc.
        // x = k∑i=1​ai​Mi​yi (mod M)
        // Mi = M/mi where M is the common modulus (m1 * m2 * ... * mk)
        // yi satisfies Miyi ≡ 1 (mod mi)

        long t; // earliest timestamp such that all of the listed bus IDs depart at offsets matching their positions
                // in the list
        String[] sched = input[1].split(",");
        List<Integer> busIds = new ArrayList<>();
        List<Integer> offsets = new ArrayList<>();
        for (int i = 0; i < sched.length; i++) {
            if (!sched[i].equals("x")) {
                int busId = Integer.parseInt(sched[i]);
                busIds.add(busId);
                offsets.add(busId - i);
            }
        }

        long M = 1;
        for (int mi : busIds) {
            M *= mi;
        }

        List<Long> Mi = new ArrayList<>();
        List<Long> yi = new ArrayList<>();
        for (int mi : busIds) {
            Mi.add(M / mi);

            long y = 1; // oops, had an int overflow
            while (y > 0) {
                if (((M / mi) * y) % mi == 1) {
                    yi.add(y);
                    y = -1;
                }
                y++;
            }
        }

        long x = 0;
        for (int i = 0; i < busIds.size(); i++) {
            x += offsets.get(i) * Mi.get(i) * yi.get(i);
        }

        t = x % M;
        return t; // must be larger than 100000000000000 (surely)
    }

    public static void main(String[] args) throws IOException {
        String[] input = Day.loadInput("day13");
        days.day13.Day13 day13 = new days.day13.Day13();
        System.out.println("part one: " + day13.part01(input));
        System.out.println("part two: " + day13.part02(input));
    }
}
