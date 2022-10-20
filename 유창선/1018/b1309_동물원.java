package day1011;

import java.util.Scanner;

public class b1309_동물원 {
    public static int[][] dp;
    public static int N;
    public static int res = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new int[N][3];
        dp[0][0] = 1; // 양쪽 다 안 넣음
        dp[0][1] = 1; // 왼쪽 칸에 넣음
        dp[0][2] = 1; // 오른쪽 칸에 넣음

        for (int i = 1; i < N; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % 9901;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 9901;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
        }

        System.out.println((dp[N - 1][0] + dp[N - 1][1] + dp[N - 1][2]) % 9901);

    }


}
