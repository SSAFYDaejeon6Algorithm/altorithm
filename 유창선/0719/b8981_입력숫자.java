package day0719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b8981_입력숫자 {
    public static void main(String[] args) throws IOException {
        int token, N;
        int from = 0, value = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] arr = new int[101];
        int[] res = new int[101];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            token = Integer.parseInt(st.nextToken());
            arr[i] = token;
        } // end input

        for (int i = 0; i < N; i++) {
            value = res[from];
            from = (value + from) % N;

            while (res[from] != 0) {
                from = (from + 1) % N;
            }
            res[from] = arr[i];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(N).append("\n");
        for (int i = 0; i < N; i++) {
            sb.append(res[i]).append(" ");
        }

        System.out.println(sb.toString());
    }
}
