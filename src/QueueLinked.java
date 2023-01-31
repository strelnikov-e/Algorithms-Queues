import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class QueueLinked<Item> implements Iterable {
    private Node first, last;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QueueLinked<String> queue = new QueueLinked<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("-")) System.out.println(queue.dequeue());
            else queue.enqueue(line);
            for (Object o : queue) {
                System.out.print(o + ", ");
            }
            System.out.println();
        }
//        String[] array = {"one","two","three","four","five","six","seven"};
//        QueueLinked<String> queue = new QueueLinked<>();
//        System.out.println(queue.isEmpty());
//        queue.enqueue(array[0]);
//        queue.enqueue(array[1]);
//        queue.enqueue(array[2]);
//        queue.enqueue(array[3]);
//        queue.enqueue(array[4]);
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.isEmpty());
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>() {
            Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                Item item = current.item;
                current = current.next;
                return item;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Item previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(Item item) {

            }

            @Override
            public void add(Item item) {

            }
        };
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    public Item dequeue() {
        Item odlFirst = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return odlFirst;
    }

    private class Node {
        Item item;
        Node next;
    }
}
