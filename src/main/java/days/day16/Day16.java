package days.day16;

import days.Day;

import java.util.*;
import java.util.stream.Collectors;

public class Day16 {
    List<Integer> myTicket = new ArrayList<>();
    List<List<Integer>> nearbyTickets = new ArrayList<>();
    Map<String, String[]> rulesForTicketFields = new HashMap<>();
    List<Integer> highs = new ArrayList<>();
    List<Integer> lows = new ArrayList<>();
    List<Integer> invalids = new ArrayList<>();

    public int part01(String[] input) {
        int errorRate = 0;
        parseInput(input);
        getAllValidTicketRanges();
        for (List<Integer> ticket : nearbyTickets) {
            valueLoop:
            for (int value : ticket) {
                for (int i = 0; i < highs.size(); i++) {
                    if (value <= highs.get(i) && value >= lows.get(i)) {
                        continue valueLoop;
                    }
                }
                invalids.add(value);
            }
        }

        for (int num : invalids) {
            errorRate += num;
        }
        return errorRate;
    }

    public long part02(String[] input) {
        long departure = 1;
        removeInvalidTickets();
        Map<String, List<Integer>> possibleMatches  = new HashMap<>();

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
                while (j < nearbyTickets.size()) {
                    if (i >= nearbyTickets.get(0).size()) {
                        break;
                    }
                    List<Integer> ticket = nearbyTickets.get(j);
                    if ((ticket.get(i) < range.get(0) || ticket.get(i) > range.get(1)) && (ticket.get(i) < range.get(2) || ticket.get(i) > range.get(3))) {
                        i++;
                        j = 0;
                    } else {
                        j++;
                    }
                }
                if (i < 20) {
                    indices.add(i);
                }
                j=0;
                i++;
            }
            possibleMatches.put(pair.getKey(), indices);

        }
        // printed out all the possible positions for each field and figured out the correct mappings by hand lol
        System.out.println(possibleMatches);
        departure *= myTicket.get(17);
        departure *= myTicket.get(18);
        departure *= myTicket.get(6);
        departure *= myTicket.get(9);
        departure *= myTicket.get(19);
        departure *= myTicket.get(0);
        return departure;
    }

    public void removeInvalidTickets() {
        Iterator<List<Integer>> it = nearbyTickets.iterator();
        while (it.hasNext()) {
            List<Integer> ticket = it.next();
            for (int value : ticket) {
                if (invalids.contains(value)) {
                    it.remove();
                }
            }
        }
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
            }
        }
    }

    public void getNearbyTickets(String[] input, int start) {
        for (int i = start; i < input.length; i++) {
            List<Integer> ticket = Arrays.stream(input[i].split(",")).map(Integer::valueOf).collect(Collectors.toList());
            nearbyTickets.add(ticket);
        }
    }

    public void getAllValidTicketRanges() {
        for (Map.Entry<String, String[]> pair : rulesForTicketFields.entrySet()) {
            String[] value = pair.getValue();
            for (String st : value) {
                String[] range = st.split("-");
                lows.add(Integer.parseInt(range[0]));
                highs.add(Integer.parseInt(range[1]));
            }
        }
    }

    public static void main(String[] args) {
        String[] input = Day.loadInput("day16");
        days.day16.Day16 day16 = new days.day16.Day16();
        System.out.println(day16.part01(input));
        System.out.println(day16.part02(input));
    }
}
