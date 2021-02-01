package days.day23;

import java.util.HashMap;
import java.util.Map;

public class CircularLinkedList {
    private Node last;
    private int count;
    Map<Integer, Node> hmap = new HashMap<>();

    public CircularLinkedList() {
        this.last = null;
    }

    public CircularLinkedList(Node last) {
        this.last = last;
        if (this.last != null) {
            this.last.next = last;
        }
    }

    public int getLast() {
        return last.data;
    }

    public int getFirst() {
        return last.next.data;
    }

    public int getNext(int data) {
        int next = 0;
        if (last != null) {
            Node curr = last;
            do {
                if (curr.data == data) {
                    next = curr.next.data;
                    break;
                }
                curr = curr.next;
            } while (curr != last);
        }
        return next;
    }

    void add(int data) {
        this.count++;
        Node n = new Node(data);
        if (last == null) {
            n.next = n;
        } else {
            n.next = last.next;
            last.next = n;
        }
        last = n;
    }

    void addAfter(int insertAfter, int data) {
        this.count++;
        if (last != null) {
            Node curr = last;
            do {
                if (curr.data == insertAfter) {
                    Node n = new Node(data);
                    n.next = curr.next;
                    curr.next = n;
                    break;
                }
                curr = curr.next;
            } while (curr != last);
        }
    }

    Node removeNext(int data) {
        this.count--;
        if (last == null) return null;
        Node toDelete = null;
        Node curr = last;
        do {
            if (curr.data == data) {
                toDelete = curr.next;
                if (curr.next == curr) {
                    last = null;
                } else {
                    curr.next = toDelete.next;
                    if (toDelete == last) last = curr;
                }
                break;
            }
            curr = curr.next;
        } while (curr != last);
        return toDelete;
    }

    public boolean contains(int target) {
        boolean found = false;
        Node curr = last;
        do {
            if (curr.data == target) {
                found = true;
            }
            curr = curr.next;
        } while (curr != last && !found);
        return found;
    }

    int size() {
        return this.count;
    }

    @Override
    public String toString() {
        String s;
        Node n;
        if (last == null)
            return "null";
        s = "[";
        n = last.next;
        int i = 0;
        while (i < size()) {
            i++;
            s += n.data;
            if (n.next != last.next) s += ", ";
            n = n.next;
        }
        return s + "]";
    }
}
