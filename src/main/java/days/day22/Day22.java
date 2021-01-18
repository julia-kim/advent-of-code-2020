package days.day22;

import days.Day;

import java.util.*;

public class Day22 {
    public long part01(String[] input) {
        long winningScore = 0;
        LinkedList<Integer> player01 = new LinkedList<>();
        LinkedList<Integer> player02 = new LinkedList<>();
        initGame(input, player01, player02);

        while (!(player01.size() == 0 || player02.size() == 0)) {
            playRound(player01, player02);
        }

        LinkedList<Integer> winner;
        if (!(player01.size() == 0)) {
            winner = player01;
        } else {
            winner = player02;
        }

        int totalCards = winner.size();
        for (int i = 1; i <= totalCards; i++) {
            winningScore += i * winner.removeLast();
        }

        return winningScore;
    }

    public long part02(String[] input) {
        long winningScore = 0;
        LinkedList<Integer> player01 = new LinkedList<>();
        LinkedList<Integer> player02 = new LinkedList<>();
        initGame(input, player01, player02);
        Map<String, List<LinkedList<Integer>>> history = new HashMap<>();

        history.put("p1", new ArrayList<>());
        history.put("p2", new ArrayList<>());

        String playTillWin = playRecursiveRound(player01, player02, history);
        LinkedList<Integer> winner;
        if (playTillWin.equals("p1")) {
            winner = player01;
        } else {
            winner = player02;
        }

        int totalCards = winner.size();
        for (int i = 1; i <= totalCards; i++) {
            winningScore += i * winner.removeLast();
        }

        return winningScore;
    }

    public void initGame(String[] input, LinkedList<Integer> p1, LinkedList<Integer> p2) {
        boolean isP1 = true;
        for (String line : input) {
            if (line.equals("Player 1:") || line.equals("")) {
                continue;
            }
            if (line.equals("Player 2:")) {
                isP1 = false;
                continue;
            }
            if (isP1) {
                p1.add(Integer.parseInt(line));
            } else {
                p2.add(Integer.parseInt(line));
            }
        }
    }

    public void playRound(LinkedList<Integer> p1, LinkedList<Integer> p2) {
        if (p1.getFirst() > p2.getFirst()) {
            p1.add(p1.removeFirst());
            p1.add(p2.removeFirst());
        } else {
            p2.add(p2.removeFirst());
            p2.add(p1.removeFirst());
        }
    }

    public String playRecursiveRound(LinkedList<Integer> p1, LinkedList<Integer> p2, Map<String,
            List<LinkedList<Integer>>> previousRounds) {
//        System.out.println("p1 deck: " + p1);
//        System.out.println("p2 deck: " + p2);

        if (p1.size() == 0 || p2.size() == 0) {
            return !(p1.size() == 0) ? "p1" : "p2";
        }
        if (previousRounds.get("p1").contains(p1) && previousRounds.get("p2").contains(p2)) {
            System.out.println(p1);
            System.out.println(p2);
            System.out.println(previousRounds);
            return "p1";
        }
        List<LinkedList<Integer>> p1Prev = previousRounds.get("p1");
        p1Prev.add(new LinkedList<>(p1));
        List<LinkedList<Integer>> p2Prev = previousRounds.get("p2");
        p2Prev.add(new LinkedList<>(p2));
        previousRounds.put("p1", p1Prev);
        previousRounds.put("p2", p2Prev);

        if (p1.getFirst() <= p1.size() - 1 && p2.getFirst() <= p2.size() - 1) {
            System.out.println("going into mini game...");
            // Each game begins with its own empty set of hands it's seen.
            Map<String, List<LinkedList<Integer>>> history = new HashMap<>();
            history.put("p1", new ArrayList<>());
            history.put("p2", new ArrayList<>());
            playMiniRecursiveRound(p1, p2, history);
        } else {

            if (p1.getFirst() > p2.getFirst()) {
                p1.add(p1.removeFirst());
                p1.add(p2.removeFirst());
            } else {
                p2.add(p2.removeFirst());
                p2.add(p1.removeFirst());
            }
        }

        return playRecursiveRound(p1, p2, previousRounds);
    }

    public void playMiniRecursiveRound(LinkedList<Integer> p1, LinkedList<Integer> p2, Map<String,
            List<LinkedList<Integer>>> previousRounds) {
        List<Integer> p1subList = p1.subList(1, p1.get(0)+1);
        List<Integer> p2subList = p2.subList(1, p2.get(0)+1);
        LinkedList<Integer> p1Copy = new LinkedList<>(p1subList);
        LinkedList<Integer> p2Copy = new LinkedList<>(p2subList);

        String winner = playRecursiveRound(p1Copy, p2Copy, previousRounds);
        if (winner.equals("p1")) {
            System.out.println("p1 wins the sub-game");
            p1.add(p1.removeFirst());
            p1.add(p2.removeFirst());
        } else {
            System.out.println("p2 wins the sub-game");
            p2.add(p2.removeFirst());
            p2.add(p1.removeFirst());
        }
    }

    public static void main(String[] args) {
        String[] input = Day.loadInput("day22");
        days.day22.Day22 day22 = new days.day22.Day22();
        System.out.println("part one: " + day22.part01(input));
        System.out.println("part two: " + day22.part02(input));
    }
}
