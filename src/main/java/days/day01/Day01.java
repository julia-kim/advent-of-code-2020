package days.day01;

import java.io.*;
import java.util.ArrayList;

public class Day01 {

    public int part01(int[] r) {
        int answer = 0;
        int sum = 0;

        for (int i = 0; i < r.length; i++) {
            for (int j = 1; j < r.length; j++) {
                sum = r[i] + r[j];
                if (sum == 2020) {
                    answer = r[i] * r[j];
                }
            }
        }
        return answer;
    }

    public int part02(int[] r) {
        int answer = 0;
        int sum = 0;

        for (int i = 0; i < r.length; i++) {
            for (int j = 1; j < r.length; j++) {
                for (int k = 2; k < r.length; k++) {
                    sum = r[i] + r[j] + r[k];
                    if (sum == 2020) {
                        answer = r[i] * r[j] * r[k];
                    }
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        Day01 day01 = new Day01();
        InputStream input = Day01.class.getClassLoader().getResourceAsStream("day01.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(input));

        try {
            ArrayList<Integer> expenseReport = new ArrayList<>();
            String line;

            while ((line = in.readLine()) != null) {
                int expense = Integer.parseInt(line);
                expenseReport.add(expense);
            }

            int[] arr = expenseReport.stream().mapToInt(i -> i).toArray();

            System.out.println(day01.part01(arr));
            System.out.println(day01.part02(arr));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
