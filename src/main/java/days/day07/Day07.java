package days.day07;

import days.Day;

import java.io.IOException;
import java.util.*;

public class Day07 {
    private Graph graph;

    public Day07(String[] input) {
        this.graph = new Graph();
        Map<String, Bag> bagMap = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            input[i] = input[i].replace("bags", "").replace("bag", "").replace("contain ", "").replace(".", "").replace(
                    ",",
                    "").replace("no other", "").replaceAll("\\s+$", "");
            String[] regulation = input[i].split("  ");
            String[] info = Arrays.copyOfRange(regulation, 1, regulation.length);
            bagMap.put("bag" + i, new Bag(regulation[0]));
            for (String st : info) {
                String[] bagInfo = st.split("(?<=[0-9]) ");
                bagMap.get("bag" + i).addContents(bagInfo[1], Integer.parseInt(bagInfo[0]));
            }
        }
        for (String bag : bagMap.keySet()) {
            graph.addBag(bagMap.get(bag));
        }
    }

    public int part01() {
        Graph g = this.graph;
        String target = "shiny gold";
        int foundBags = 0;

        for (Bag bag : g.getBags()) {
            if (search(g, bag.getColor(), target)) {
                foundBags++;
            }
        }

        return foundBags;
    }

    private boolean search(Graph g, String color, String target) {
        for (String innerBagColor : g.getBag(color).getContents().keySet()) {
            if (innerBagColor.equals(target)) {
                return true;
            }
            if (search(g, innerBagColor, target)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        String[] input = Day.loadInput("day07");
        Day07 day07 = new Day07(input);
        System.out.println(day07.part01());
        // System.out.println(day07.part02());
    }
}
