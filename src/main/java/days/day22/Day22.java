package days.day22;

import days.Day;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Day22 {
    enum Player {P1, P2}

    public long part01(String[] input) {
        LinkedList<Integer> p1 = new LinkedList<>();
        LinkedList<Integer> p2 = new LinkedList<>();
        initGame(input, p1, p2);

        while (!(p1.isEmpty() || p2.isEmpty())) {
            playRound(p1, p2);
        }

        LinkedList<Integer> winner = p1.size() > 0 ? p1 : p2;
        return calculateScore(winner);
    }

    public long part02(String[] input) {
        LinkedList<Integer> p1 = new LinkedList<>();
        LinkedList<Integer> p2 = new LinkedList<>();
        Set<LinkedList<Integer>> playedGames = new HashSet<>();
        initGame(input, p1, p2);
        return calculateScore(playRecursiveCombat(p1, p2, playedGames) == Player.P1 ? p1 : p2);
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
            collectCards(p1, p2);
        } else {
            collectCards(p2, p1);
        }
    }

    public Player playRecursiveCombat(LinkedList<Integer> p1, LinkedList<Integer> p2, Set<LinkedList<Integer>> playedGames) {
        /* RULES OF RECURSIVE COMBAT:
         *      1. If a particular configuration was seen before in the game, the game instantly ends in a win for
         *         player 1 (prevents infinite games).
         *      2. Otherwise, both players draw their top card, and if either player's number is higher than the amount
         *         of cards in their hand, the player with the higher-valued card wins the round.
         *      3. If both players have at least as many cards remaining in their deck as the value of the card they
         *         just drew, the winner of the round is determined by playing a sub-game.
         *      4. The sub-game is played with a copy of the the player's deck (the quantity of cards copied is equal
         *         to the number on the card they drew to trigger the sub-game).
         */

        if (p1.isEmpty()|| p2.isEmpty()) {
            return p1.size() > 0 ? Player.P1 : Player.P2;
        }

        if (playedGames.contains(p1)) {
            return Player.P1;
        }

        playedGames.add(new LinkedList<>(p1));

        if (p1.getFirst() < p1.size() && p2.getFirst() < p2.size()) {
            // System.out.println("going into mini game...");
            // each game begins with its own empty set of hands it's seen
            playSubGame(p1, p2, new HashSet<>());
        } else {
            playRound(p1, p2);
        }

        return playRecursiveCombat(p1, p2, playedGames);
    }

    public void playSubGame(LinkedList<Integer> p1, LinkedList<Integer> p2, Set<LinkedList<Integer>> playedGames) {
        LinkedList<Integer> p1Copy = new LinkedList<>(p1.subList(1, p1.get(0) + 1));
        LinkedList<Integer> p2Copy = new LinkedList<>(p2.subList(1, p2.get(0) + 1));

        Player winner = playRecursiveCombat(p1Copy, p2Copy, playedGames);
        if (winner == Player.P1) {
            collectCards(p1, p2);
        } else {
            collectCards(p2, p1);
        }
    }

    public void collectCards(LinkedList<Integer> winner, LinkedList<Integer> loser) {
        winner.add(winner.removeFirst());
        winner.add(loser.removeFirst());
    }

    public long calculateScore(LinkedList<Integer> deck) {
        long score = 0;
        int totalCards = deck.size();
        for (int i = 1; i <= totalCards; i++) {
            score += i * deck.removeLast();
        }
        return score;
    }

    public static void main(String[] args) {
        String[] input = Day.loadInput("day22");
        days.day22.Day22 day22 = new days.day22.Day22();
        System.out.println("part one: " + day22.part01(input));
        System.out.println("part two: " + day22.part02(input));
    }
}
