import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private final int head;
    private int tail;
    private Item[] q;

    // construct an empty randomized queue
    public RandomizedQueue() {
        head = 0;
        tail = 0;
        q = (Item[]) new Object[1];
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        System.out.println("\nenqueue, dequeue and size methods test:");
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        System.out.print("\t");
        for (int i : queue) System.out.print(i + " ");
        System.out.println("\t| size: " + queue.size() + "\t| is empty? " + queue.isEmpty());
        System.out.println("\tSample(): " + queue.sample());
        System.out.println("\tSample(): " + queue.sample());
        System.out.println("\tRemoved objects:");
        System.out.print("\t");
        for (int i = 0; i < 10; i++) {
            System.out.print(queue.dequeue() + " ");
        }
        System.out.println("\t| size: " + queue.size() + "\t| is empty? " + queue.isEmpty());

        System.out.print("Test of two iterators:\n");
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }
        for (int a : queue) {
            System.out.print("\t");
            for (int b : queue) {
                System.out.print(a + "-" + b + " ");
            }
            System.out.println();
        }

        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.println("\t\t\t\t\t| size: " + queue.size() + "\t| is empty? " + queue.isEmpty());

        try {
            iterator.next();
        } catch (NoSuchElementException e) {
            System.out.println("iterator.next(): NoSuchElementException thrown");
        }
        try {
            queue.enqueue(null);
        } catch (IllegalArgumentException e) {
            System.out.println("enqueue(null): IllegalArgumentException thrown");
        }
        try {
            for (int i = 0; i < 10; i++) {
                queue.dequeue();
            }
        } catch (NoSuchElementException e) {
            System.out.println("dequeue(): NoSuchElementException thrown");
        }
        try {
            iterator.remove();
        } catch (UnsupportedOperationException e) {
            System.out.println("iterator.remove(): UnsupportedOperationException thrown");
        }


    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return q[head] == null;
    }

    // return the number of items on the randomized queue
    public int size() {
        if (isEmpty()) return 0;
        else return tail - head;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (tail == q.length) resize(q.length * 2);
        q[tail++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (q[head] == null) throw new NoSuchElementException();
        int i = random();
        Item item = q[i];
        q[i] = q[--tail];
        q[tail] = null;
        if (size() < q.length / 4) resize(q.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (q[head] == null) throw new NoSuchElementException();
        return q[random()];
    }

    private int random() {
        return StdRandom.uniformInt(size());
    }

    private void resize(int newSize) {
        Item[] newQ = (Item[]) new Object[newSize];
        for (int i = 0; i < size(); i++) {
            newQ[i] = q[i];
        }
        q = newQ;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private final Item[] newQ;
        private int current;

        public QueueIterator() {
            current = head;
            int n = size();
            newQ = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                newQ[i] = q[i];
            }
            StdRandom.shuffle(newQ);
        }

        @Override
        public boolean hasNext() {
            if (current == newQ.length) return false;
            return (newQ[current] != null);
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = newQ[current];
            current++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}