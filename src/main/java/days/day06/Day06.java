package days.day06;

import days.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day06 {
    public int part01(String[] input) {
        ArrayList<String> answers = getEachGroupsAnswers(input);
        int countsSum = 0;
        for (String answer : answers) {
            HashMap<Character, Character> anyYeses = new HashMap<>();
            for (int i = 0; i < answer.length(); i++) {
                anyYeses.put(answer.charAt(i), 'c');
            }
            countsSum += anyYeses.size();
        }
        return countsSum;
    }

    public int part02(String[] customsForms) {
        int countsSum = 0;
        String answer = "";
        int persons = 0;
        for (int i = 0; i < customsForms.length; i++) {
            answer += customsForms[i];
            persons++;
            if (customsForms[i].isBlank() || i == customsForms.length - 1) {
                persons--;
                if (i == customsForms.length - 1) {
                    persons++;
                }
                HashMap<Character, Integer> answerCount = new HashMap<Character, Integer>();
                for (int j = 0; j < answer.length(); j++) {
                    char c = answer.charAt(j);
                    if (answerCount.get(c) != null) {
                        answerCount.put(c, answerCount.get(c) + 1);
                    } else {
                        answerCount.put(c, 1);
                    }
                }
                
                for (int k = 97; k <= 122; k++) {
                    if (answerCount.containsKey((char) k)) {
                        if (answerCount.get((char) k) == persons) {
                            countsSum++;
                        }
                    }
                }
                answer = "";
                persons = 0;
            }
        }

        return countsSum;
    }

    public ArrayList<String> getEachGroupsAnswers(String[] customsForms) {
        ArrayList<String> answers = new ArrayList<>();
        String st = "";

        for (int i = 0; i < customsForms.length; i++) {
            st += customsForms[i];
            if (customsForms[i].isBlank() || i == customsForms.length - 1) {
                answers.add(st);
                st = "";
            }
        }
        return answers;
    }

    public static void main(String[] args) throws IOException {
        String[] input = Day.loadInput("day06");
        Day06 day06 = new Day06();
        System.out.println(day06.part01(input));
        System.out.println(day06.part02(input));
    }
}
