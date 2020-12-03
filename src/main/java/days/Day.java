package days;

import days.day03.Day03;

import java.io.*;
import java.util.ArrayList;

public class Day {
    public static String[] loadInput(String day) {
        InputStream input = Day03.class.getClassLoader().getResourceAsStream(day + ".txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        try {
            ArrayList<String> arrList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String st = line;
                arrList.add(st);
            }

            return arrList.toArray(new String[arrList.size()]);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void main(String[] args) {

    }
}
