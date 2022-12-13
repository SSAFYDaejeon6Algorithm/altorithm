package day1108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class b2374_같은수로만들기 {
    static int N;
    static int[] arr;
    static int[] tmpArr;
    static ArrayList<Integer> list = new ArrayList<>();
    static Stack<Integer> stack = new Stack<>();
    static long cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        tmpArr = new int[N];


        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
            if (stack.isEmpty()) stack.push(num);
            else if (stack.peek() < num) {
                cnt += num - stack.pop();
                stack.push(num);
            } else if (stack.peek() > num) {
                stack.pop();
                stack.push(num);
            }
        }
        int max = Arrays.stream(arr).max().getAsInt();
        cnt += max - stack.pop();
        System.out.println(cnt);
    }
}
