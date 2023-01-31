/*
Use array q[] to store items in queue;
enqueue() add item in [tail]
dequeue() remove item from q[head];
update head and tail modulo the capacity. If last is bigger than q.length then move last pointer to q[0];
add resizing array;
 */

import java.util.Arrays;
import java.util.Scanner;

public class QueueArray {
    private int head, tail;
    private String[] q;

    public QueueArray(int size) {
        q = new String[size];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QueueArray queue = new QueueArray(1);
        while (scanner.hasNextLine()) {
            String entry = scanner.nextLine();
            if (entry.equals("-")) queue.dequeue();
            else queue.enqueue(entry);
            System.out.println(queue);
        }

//        String[] array = {"one","two","three","four","five","six","seven"};
//        System.out.println(queue.isEmpty());
//        queue.enqueue(array[0]);
//        System.out.println(queue);
//        queue.dequeue();
//        queue.enqueue(array[0]);
//        System.out.println(queue);
//        queue.enqueue(array[1]);
//        System.out.println(queue);
//        queue.enqueue(array[2]);
//        System.out.println(queue);
//        queue.enqueue(array[3]);
//        System.out.println(queue);
//        queue.enqueue(array[4]);
//        System.out.println(queue);
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        queue.enqueue(array[5]);
//        queue.enqueue(array[6]);
//        queue.enqueue(array[4]);
//        System.out.println(queue);
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.isEmpty());
//        System.out.println(queue);
    }

    public boolean isEmpty() {
        return q[head] == null;
    }

    public void enqueue(String item) {
        if (isEmpty()) {
            q[head] = item;
            tail = head;
            return;
        }
        if (!isEmpty() && (head == tail)) increase();
        q[tail++] = item;
        if (tail >= q.length) tail = 0;
    }

    public String dequeue() {
        String odlHead = q[head];
        q[head] = null;
        if (head >= q.length - 1) head = 0;
        else head++;
        if (q.length != 2 && Math.abs(tail - head) <= q.length / 4) decrease();
        return odlHead;
    }

    private void increase() {
        String[] newQ = new String[q.length * 2];
        int j = 0;
        if (head < tail) {
            for (int i = head; i < tail; i++) {
                newQ[j++] = q[i];
            }
        } else {
            for (int i = head; i < q.length; i++) {
                if (q[i] != null) newQ[j++] = q[i];
            }
            for (int i = 0; i < tail; i++) {
                if (q[i] != null) newQ[j++] = q[i];
            }
        }
        q = newQ;
        head = 0;
        tail = j;
        System.out.println(head + ", " + tail);
    }

    private void decrease() {
        String[] newQ = new String[q.length / 2];
        int j = 0;
        int size = Math.abs(head - tail);
        if (head < tail) {
            for (int i = head; i < tail; i++) {
                newQ[j++] = q[i];
            }
        } else {
            for (int i = head; i < q.length; i++) {
                newQ[j++] = q[i];
            }
            for (int i = 0; i < tail; i++) {
                newQ[j++] = q[i];
            }
        }
        q = newQ;
        head = 0;
        tail = j;
    }

    //
    @Override
    public String toString() {
        return Arrays.toString(q) +
                "\t  head=" + head +
                ", tail=" + tail;
    }
}
