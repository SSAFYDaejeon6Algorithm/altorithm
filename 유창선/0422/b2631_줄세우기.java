package day0422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class b2631_줄세우기 {
    static ArrayList<Integer> list = new ArrayList<>();
    static int[] LIS;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        LIS = new int[N];
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(br.readLine()));
        } // end input

        Arrays.fill(LIS, 1);

        int maxIncrease = Integer.MIN_VALUE;
//        int maxIndex = -1;
        for (int i = 0; i < N; i++) {
            for (int j =0; j <= i - 1; j++) {
                if (list.get(j) < list.get(i) && LIS[i] < LIS[j] + 1)
                    LIS[i] = LIS[j] + 1;
            }

            if (maxIncrease < LIS[i]) {
                maxIncrease = LIS[i];
//                maxIndex = i;
            }
        }
        System.out.println(N - maxIncrease);

    }
}
