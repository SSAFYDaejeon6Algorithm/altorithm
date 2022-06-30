package day0628;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2229_조짜기 {
    public static int[] arr, res;
    public static int N, max, min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[N];
        res = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = arr[i];
            min = arr[i];
            for (int j = i; j >= 0; j--) {
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                if (j - 1 < 0) res[i] = Math.max(res[i], max - min);
                else res[i] = Math.max(res[i], res[j - 1] + max - min);

            }
        }
        System.out.println(res[N - 1]);
    }
}
