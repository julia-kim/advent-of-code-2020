package days.day07;

import days.Day;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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

    public int part02() {
        Graph g = this.graph;
        String target = "shiny gold";
        Map<Bag, Integer> visited = new LinkedHashMap<>();

        containsBag(g, target, visited);
        System.out.println(visited);
        int bagCount = visited.get(g.getBag(target));
        for (Bag bag : visited.keySet()) {
            for (String innerBag : bag.getContents().keySet()) {
                Bag inner = g.getBag(innerBag);
                if (visited.get(inner) != 0) {
                    bagCount += bag.getContents().get(innerBag) * calculateBags(g, visited, bag, visited.get(inner));
                    System.out.println(innerBag + " " + bag.getContents().get(innerBag) + " * " + calculateBags(g,
                            visited,
                            bag,
                            visited.get(inner)));
                }
            }
        }

        return bagCount;
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

    private void containsBag(Graph g, String target, Map<Bag, Integer> visited) {
        for (Bag bag : g.getBags()) {
            int totalInnerBags = 0;
            if (bag.getColor().equals(target) && !visited.containsKey(bag)) {
                for (int i : bag.getContents().values()) {
                    totalInnerBags += i;
                }
                visited.put(bag, totalInnerBags);
                for (String innerBag : bag.getContents().keySet()) {
                    containsBag(g, innerBag, visited);
                }
            }
        }
    }

    private int calculateBags(Graph g, Map<Bag, Integer> visited, Bag bag, int i) {
        for (String innerBag : bag.getContents().keySet()) {
            Bag inner = g.getBag(innerBag);
            if (visited.get(inner) != 0) {
                return i * calculateBags(g, visited, inner, visited.get(inner));
            }
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        String[] input = Day.loadInput("day07");
        Day07 day07 = new Day07(input);
        System.out.println(day07.part01());
        System.out.println(day07.part02());
    }
}
