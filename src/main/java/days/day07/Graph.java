package days.day07;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Bag> bags;

    public Graph() {
        this.bags = new ArrayList<>();
    }

    public Graph(List<Bag> bags) {
        this.bags = bags;
    }

    public void addBag(Bag a) {
        this.bags.add(a);
    }

    public List<Bag> getBags() {
        return bags;
    }

    public Bag getBag(String searchColor) {
        for (Bag bag : this.getBags()) {
            if (bag.getColor().equals(searchColor)) {
                return bag;
            }
        }
        return null;
    }

    public int getSize() {
        return this.bags.size();
    }

    @Override
    public String toString() {
        return "Graph{" +
                "bags=" + bags +
                "}";
    }
}

