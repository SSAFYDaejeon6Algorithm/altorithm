package day1024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b3079_입국심사 {
    static int N, M;
    static long[] arr;
    static long ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        } // end input
        long lt = 1;
        long rt = Arrays.stream(arr).max().getAsLong() * M;

        while (lt <= rt) {
            long cnt = 0;
            long time = (lt + rt) / 2;
            boolean flag = false;
            for (int i = 0; i < N; i++) {
                cnt += (time) / arr[i];
                if (cnt >= M) {
                    flag = true;
                    ans = time;
                    break;
                }
            } // end for

            if (flag) rt = time - 1;
            else lt = time + 1;
        }

        System.out.println(ans);
    }
}
