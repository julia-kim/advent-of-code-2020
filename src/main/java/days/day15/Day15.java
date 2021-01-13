package days.day15;

import days.Day;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day15 {
    public int play(String[] input, int n) {
        Map<Integer, LinkedList<Integer>> game = new HashMap<>();
        List<Integer> startingNumbers = Arrays.stream(input[0].split(",")).map(Integer::parseInt).collect(Collectors.toList());

        for (int i = 1; i <= startingNumbers.size(); i++) {
            LinkedList<Integer> list = new LinkedList<>();
            list.add(i);
            game.put(startingNumbers.get(i - 1), list);
        }

        int turn = game.size() + 1;
        Integer lastSpokenNumber = startingNumbers.get(startingNumbers.size() - 1);
        while (turn <= n) {
            LinkedList<Integer> turnIndices;
            lastSpokenNumber = (game.get(lastSpokenNumber).size() <= 1) ? 0 : (turn - 1) - game.get(lastSpokenNumber).removeFirst();
            turnIndices = !game.containsKey(lastSpokenNumber) ? new LinkedList<>() : game.get(lastSpokenNumber);
            turnIndices.add(turn);
            game.put(lastSpokenNumber, turnIndices);
            turn++;
        }

        return lastSpokenNumber;
    }

    public static void main(String[] args) throws IOException {
        String[] input = Day.loadInput("day15");
        days.day15.Day15 day15 = new days.day15.Day15();
        System.out.println("part one: " + day15.play(input, 2020));
        System.out.println("part two: " + day15.play(input, 30000000));
    }
}
