import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class b1835 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Deque<Integer> deque = new LinkedList<>();

        deque.push(n);
        for (int i = n - 1; i >= 1; i--) {
            deque.push(i);

            for (int j = 0; j < i; j++) {
                int cur = deque.pollLast();
                deque.push(cur);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.poll()).append(" ");
        }
        System.out.println(sb.toString());
    }
}
