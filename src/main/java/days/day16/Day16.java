package days.day16;

import days.Day;

import java.util.*;
import java.util.stream.Collectors;

public class Day16 {
    List<Integer> myTicket;
    List<List<Integer>> nearbyTickets;
    Map<String, String[]> rulesForTicketFields;

    public Day16(String[] input) {
        myTicket = new ArrayList<>();
        nearbyTickets = new ArrayList<>();
        rulesForTicketFields = new HashMap<>();
        parseInput(input);
    }

    public int part01() {
        int errorRate = 0;

        List<Integer> rangeMins = new ArrayList<>();
        List<Integer> rangeMaxes = new ArrayList<>();
        getAllValidTicketRanges(rangeMins, rangeMaxes);

        List<Integer> invalidValues = new ArrayList<>();

        for (List<Integer> ticket : nearbyTickets) {
            valueLoop:
            for (int value : ticket) {
                for (int i = 0; i < rangeMaxes.size(); i++) {
                    if (value <= rangeMaxes.get(i) && value >= rangeMins.get(i)) {
                        continue valueLoop;
                    }
                }
                invalidValues.add(value);
            }
        }

        removeInvalidTickets(invalidValues);

        for (int num : invalidValues) {
            errorRate += num;
        }
        return errorRate;
    }

    public long part02() {
        long departure = 1;

        Map<String, List<Integer>> possibleMatches = new HashMap<>();

        for (Map.Entry<String, String[]> pair : rulesForTicketFields.entrySet()) {
            List<Integer> range = new ArrayList<>(4);
            String[] value = pair.getValue();
            for (String st : value) {
                String[] r = st.split("-");
                range.add(Integer.parseInt(r[0]));
                range.add(Integer.parseInt(r[1]));
            }

            List<Integer> indices = new ArrayList<>();
            int i = 0;
            int j = 0;
            while (i < nearbyTickets.get(0).size()) {
                while (j < nearbyTickets.size() && i < nearbyTickets.get(0).size()) {
                    List<Integer> ticket = nearbyTickets.get(j);
                    if ((ticket.get(i) < range.get(0) || ticket.get(i) > range.get(1)) && (ticket.get(i) < range.get(2) || ticket.get(i) > range.get(3))) {
                        i++; // if number is out of range, try next value on ticket
                        j = 0; // restart loop to recheck all tickets
                    } else {
                        j++;
                    }
                }
                if (i < nearbyTickets.get(0).size()) {
                    indices.add(i);
                }
                i++;
                j = 0;
            }
            possibleMatches.put(pair.getKey(), indices);
        }

        int k = possibleMatches.size() - 1;
        while (k >= 0) {
            for (Map.Entry<String, List<Integer>> entry : possibleMatches.entrySet()) {
                int target;
                if (entry.getValue().size() == 1) {
                    target = entry.getValue().get(0);
                    removePosition(possibleMatches, target);
                }
            }
            k--;
        }

        for (Map.Entry<String, List<Integer>> entry : possibleMatches.entrySet()) {
            if (entry.getKey().startsWith("departure")) {
                departure *= myTicket.get(entry.getValue().get(0));
            }
        }
        return departure;
    }

    public void parseInput(String[] input) {
        boolean isField = true;
        for (int i = 0; i < input.length; i++) {
            if (isField && !input[i].equals("")) {
                String[] rule = input[i].split(":\\s");
                String fieldName = rule[0];
                String[] validRanges = rule[1].split("\\sor\\s");
                rulesForTicketFields.put(fieldName, validRanges);
            }
            if (input[i].equals("")) {
                isField = false;
            }
            if (input[i].equals("your ticket:")) {
                myTicket = Arrays.stream(input[i + 1].split(",")).map(Integer::valueOf).collect(Collectors.toList());
            }
            if (input[i].equals("nearby tickets:")) {
                getNearbyTickets(input, i + 1);
                break;
            }
        }
    }

    public void getNearbyTickets(String[] input, int start) {
        for (int i = start; i < input.length; i++) {
            List<Integer> ticket = Arrays.stream(input[i].split(",")).map(Integer::valueOf).collect(Collectors.toList());
            nearbyTickets.add(ticket);
        }
    }

    public void getAllValidTicketRanges(List<Integer> rangeMins, List<Integer> rangeMaxes) {
        for (Map.Entry<String, String[]> pair : rulesForTicketFields.entrySet()) {
            String[] value = pair.getValue();
            for (String st : value) {
                String[] range = st.split("-");
                rangeMins.add(Integer.parseInt(range[0]));
                rangeMaxes.add(Integer.parseInt(range[1]));
            }
        }
    }

    public void removeInvalidTickets(List<Integer> invalidValues) {
        // iterators make it possible to remove elements from the collection being iterated over
        Iterator<List<Integer>> iter = nearbyTickets.iterator();
        while (iter.hasNext()) {
            List<Integer> ticket = iter.next();
            for (int value : ticket) {
                if (invalidValues.contains(value)) {
                    iter.remove();
                }
            }
        }
    }

    public void removePosition(Map<String, List<Integer>> map, int target) {
        if (target > -1) {
            for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
                if (entry.getValue().size() > 1 && entry.getValue().contains(target)) {
                    entry.getValue().remove(Integer.valueOf(target));
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] input = Day.loadInput("day16");
        days.day16.Day16 day16 = new days.day16.Day16(input);
        System.out.println("part one: " + day16.part01());
        System.out.println("part two: " + day16.part02());
    }
}
