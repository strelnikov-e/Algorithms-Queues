import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<String> deque = new Deque<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("-")) System.out.println("Removed: " + deque.removeFirst());
            else deque.addLast(line);
            System.out.println("Size = " + deque.size());
            for (Object s : deque) {
                System.out.print(s + ", ");
            }
            System.out.println();
        }
    }
}