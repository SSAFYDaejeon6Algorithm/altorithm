package day0927;

import java.util.Scanner;

public class b2225_합분해 {
    static int N, K;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        dp = new int[K + 1][N + 1];

        dp[0][0] = 1;

        for (int k = 1; k <= K; k++) { // 1. k 개 사용해서
            for (int n = 0; n <= N; n++) { // 3. 숫자 n 만들기
                for (int i = 0; i <= n; i++) { // 2. 마지막 숫자 i를 사용해서
                    dp[k][n] = (dp[k][n] + dp[k - 1][n - i]) % 1000000000;
                }
            }
        }

        System.out.println(dp[K][N]);
    }


}
