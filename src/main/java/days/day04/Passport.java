package days.day04;

import java.util.HashMap;

public class Passport {
    private int byr; // (Birth Year)
    private int iyr; // (Issue Year)
    private int eyr; // (Expiration Year)
    private String hgt; // (Height)
    private String hcl; // (Hair Color)
    private String ecl; // (Eye Color)
    private String pid; // (Passport ID)
    private String cid; // (Country ID)

    public Passport(String st) {
        String[] credentials = st.split("\\s+");

        HashMap<String, String> passportFields = new HashMap<>();
        for (String credential : credentials) {
            String[] field = credential.split(":");
            for (int i = 0; i < field.length; i++) {
               passportFields.put(field[0], field[1]);
            }
        }

        if (passportFields.containsKey("byr")) {
            int birthYear = Integer.parseInt(passportFields.get("byr"));
            //  four digits; at least 1920 and at most 2002.
            if (birthYear >= 1920 && birthYear <= 2002) {
                byr = birthYear;
            }
        }
        if (passportFields.containsKey("iyr")) {
            int issueYear = Integer.parseInt(passportFields.get("iyr"));
            // four digits; at least 2010 and at most 2020.
            if (issueYear >= 2010 && issueYear <= 2020) {
                iyr = issueYear;
            }
        }
        if (passportFields.containsKey("eyr")) {
            int expiryYear = Integer.parseInt(passportFields.get("eyr"));
            //  four digits; at least 2020 and at most 2030.
            if (expiryYear >= 2020 && expiryYear <= 2030) {
                eyr = expiryYear;
            }
        }
        if (passportFields.containsKey("hgt")) {
            String height = passportFields.get("hgt");
            //  a number followed by either cm or in:
            //      if cm, the number must be at least 150 and at most 193.
            //      if in, the number must be at least 59 and at most 76.
            if (height.contains("cm")) {
                int h = Integer.parseInt(height.substring(0, height.length() - 2));
                if (h  >= 150 && h <= 193) {
                    hgt = height;
                }
            }
            if (height.contains("in")) {
                int h = Integer.parseInt(height.substring(0, height.length() - 2));
                if (h >= 59 && h <= 76) {
                    hgt = height;
                }
            }
        }
        if (passportFields.containsKey("hcl")) {
            String hairColor = passportFields.get("hcl");
            // a # followed by exactly six characters 0-9 or a-f.
            if (hairColor.charAt(0) == '#' && hairColor.length() == 7) {
                hcl = hairColor;
            }
        }
        if (passportFields.containsKey("ecl")) {
            String eyeColor = passportFields.get("ecl");
            String[] validEyeColors = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
            //  exactly one of: amb blu brn gry grn hzl oth.
            for (String color : validEyeColors) {
                if (eyeColor.equals(color)) {
                    ecl = eyeColor;
                }
            }
        }
        if (passportFields.containsKey("pid")) {
            String passportId = passportFields.get("pid");
            //  a nine-digit number, including leading zeroes.
            if (passportId.length() == 9) {
                pid = passportId;
            }
        }
        if (passportFields.containsKey("cid")) {
            //  ignored, missing or not.
            cid = passportFields.get("cid");
        }
    }

    public int getByr() {
        return byr;
    }

    public int getIyr() {
        return iyr;
    }

    public int getEyr() {
        return eyr;
    }

    public String getHgt() {
        return hgt;
    }

    public String getHcl() {
        return hcl;
    }

    public String getEcl() {
        return ecl;
    }

    public String getPid() {
        return pid;
    }

    public String getCid() {
        return cid;
    }

}
