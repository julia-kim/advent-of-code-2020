package days.day04;

import days.Day;

import java.io.IOException;
import java.util.ArrayList;

public class Day04 {

    public int part01(String[] input) {
        ArrayList<String> passports = getAllPassportsFromInput(input);
        int validPassports = 0;

        for (String st : passports) {
            String[] credentials = st.split("\\s+");
            if (credentials.length == 8) {
                validPassports++;
            }

            if (credentials.length == 7) {
                for (String credential : credentials) {
                    if (credential.contains("cid")) {
                        validPassports--;
                        break;
                    }
                }
                validPassports++;
            }
        }
        return validPassports;
    }

    public int part02(String[] input) {
        ArrayList<String> passports = getAllPassportsFromInput(input);
        int validPassports = 0;
        for (String passport : passports) {
            Passport validatedPassport = new Passport(passport);
            if (validatedPassport.getByr() != 0
                    && validatedPassport.getIyr() != 0
                    && validatedPassport.getEyr() != 0
                    && validatedPassport.getHcl() != null
                    && validatedPassport.getHgt() != null
                    && validatedPassport.getEcl() != null
                    && validatedPassport.getPid() != null) {
                validPassports++;
            }
        }

        return validPassports;
    }

    public ArrayList<String> getAllPassportsFromInput(String[] input) {
        ArrayList<String> passports = new ArrayList<>();
        String passport = "";
        for (int i = 0; i < input.length; i++) {
            passport += input[i] + " ";
            if (input[i].isBlank() || i == input.length - 1) {
                passports.add(passport);
                passport = "";
            }
        }
        return passports;
    }

    public static void main(String[] args) throws IOException {
        String[] input = Day.loadInput("day04");
        Day04 day04 = new Day04();
        System.out.println(day04.part01(input));
        System.out.println(day04.part02(input));
    }
}
