package days.day07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bag {
    private String color;
    private Map<String, Integer> contents;

    public Bag(String color) {
        this.color = color;
        this.contents = new HashMap<>();
    }

    public void addContents(String a, Integer b) {
        this.contents.put(a, b);
    }

    public String getColor() {
        return color;
    }

    public Map<String, Integer> getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return "Bag{" +
                "color=" + color +
                ", contents=" + contents +
                "}"+ "\n";
    }
}
