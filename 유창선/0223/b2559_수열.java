package day0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2559_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int sum = 0;
        st = new StringTokenizer(br.readLine()); // 온도 값들 readLine
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }
        int max = sum;
        int ptr = 0;

        for (int i = K; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum -= arr[ptr++];
            sum += arr[i];

            max = Math.max(max, sum);
        }
        System.out.println(max);
    }
}
