import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 A double-ended queue or deque (pronounced “deck”)
 is a generalization of a stack and a queue that supports adding and removing items
 from either the front or the back of the data structure
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int counter = 0;

    // construct an empty deque
    public Deque() {
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        System.out.println("\nadd, remove and size methods test: (should return elements in descending order");
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) deque.addLast(i);
            else deque.addFirst(i);
        }
        System.out.print("\t");
        for (int i : deque) System.out.print(i + " ");
        System.out.println("\t| size: " + deque.size() + "\t| is empty? " + deque.isEmpty());
        System.out.println("\tRemoved objects:");
        System.out.print("\t");
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) System.out.print(deque.removeFirst() + " ");
            else System.out.print(deque.removeLast() + " ");
        }
        System.out.println("\t| size: " + deque.size() + "\t| is empty? " + deque.isEmpty());

        System.out.print("Iterator test: \n\t");
        deque.addLast(1);
        deque.addFirst(2);
        deque.addLast(3);
        deque.addFirst(4);
        Iterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("\t\t\t\t| size: " + deque.size() + "\t| is empty? " + deque.isEmpty());
        try {
            iterator.next();
        } catch (NoSuchElementException e) {
            System.out.println("iterator.next(): NoSuchElementException thrown");
        }
        try {
            deque.addFirst(null);
        } catch (IllegalArgumentException e) {
            System.out.println("addFirst(null): IllegalArgumentException thrown");
        }
        try {
            deque.addLast(null);
        } catch (IllegalArgumentException e) {
            System.out.println("addLast(null): IllegalArgumentException thrown");
        }
        try {
            deque.removeFirst();
            deque.removeFirst();
            deque.removeFirst();
            deque.removeFirst();
            deque.removeFirst();
        } catch (NoSuchElementException e) {
            System.out.println("removeFirst(): NoSuchElementException thrown");
        }
        try {
            iterator.remove();
        } catch (UnsupportedOperationException e) {
            System.out.println("iterator.remove(): UnsupportedOperationException thrown");
        }


    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return counter;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
            counter++;
//            System.out.println("First node created "+ first.item);
            return;
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        oldFirst.previous = first;
        counter++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            last = new Node();
            last.item = item;
            first = last;
            counter++;
            return;
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        oldLast.next = last;
        counter++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        if (first.next == null) {
            first = null;
            last = null;
            counter--;
            return item;
        }
        first = first.next;
        first.previous = null;
        counter--;
//        System.out.println("remove last: first=" + first.item + " last=" + last.item);
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        if (last.previous == null) {
            last = null;
            first = null;
            counter--;
            return item;
        }
        last = last.previous;
        last.next = null;
        counter--;
//        System.out.println("remove last: first=" + first.item + " last=" + last.item);
        return item;
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
