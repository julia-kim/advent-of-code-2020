package days.day02;

import java.io.*;
import java.util.ArrayList;

public class Day02 {

    public static void main(String[] args) throws IOException {
        InputStream input = Day02.class.getClassLoader().getResourceAsStream("day02.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(input));

        try {
            ArrayList<String> passwordPolicies = new ArrayList<>();
            String line;

            while ((line = in.readLine()) != null) {
                String policy = line;
                passwordPolicies.add(policy);
            }

            String[] arr = passwordPolicies.toArray(new String[passwordPolicies.size()]);
            int validsOne = 0;
            int validsTwo = 0;
            for (String policy : arr) {
                Password pw = new Password(policy);
                int a = pw.getA();
                int b = pw.getB();
                char letter = pw.getLetter();
                String password = pw.getPassword();

                if (pw.part01(a, b, letter, password)) {
                    validsOne++;
                }
                if (pw.part02(a, b, letter, password)) {
                    validsTwo++;
                }
            }
            System.out.println(validsOne);
            System.out.println(validsTwo);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
