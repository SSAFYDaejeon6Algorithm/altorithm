package day0603;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class b17298_오큰수 {
    public static void main(String[] args) throws IOException {
        Stack<Num> stack = new Stack<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] res = new int[N];
        Arrays.fill(res, -1); // -1로 초기화
        int n = 0, i = 0;

        while(st.hasMoreTokens()) {
            n = Integer.parseInt(st.nextToken());
            if (stack.isEmpty() || stack.peek().n > n) { // 스택이 비어 있거나 전의 수가 더 크다면 push
                stack.push(new Num(n, i++));
                continue;
            }

            while (!stack.isEmpty() && stack.peek().n < n) { // 현재 n 값보다 큰 값 나올 때까지 pop 하고 오큰수 값으로 적어주기
                Num popNum = stack.pop();
                res[popNum.i] = n; // 오큰수 적어주기
            }
            stack.push(new Num(n, i++)); // 현재값 스택에 넣기
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < N; j++) {
            sb.append(res[j]).append(" ");
        }
        System.out.println(sb.toString());

    }

    static class Num {
        int n, i;

        Num(int n, int i) {
            this.n = n;
            this.i = i;
        }
    }
}
