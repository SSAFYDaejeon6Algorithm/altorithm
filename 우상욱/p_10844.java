package bj;

import java.util.Scanner;

public class p_10844 {

	static long mod = 1000000000;
	static long dp[][] = new long[101][10];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		for (int i = 1; i <= 9; i++)
			dp[1][i] = 1;

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0) {
					// dp[i + 1][1]
					dp[i + 1][j + 1] += dp[i][j];
					dp[i + 1][j + 1] %= mod;
				}
				else if (j == 9) {
					// dp[i + 1][8]
					dp[i + 1][j - 1] += dp[i][j];
					dp[i + 1][j - 1] %= mod;
				}
				else {
					dp[i + 1][j - 1] += dp[i][j];
					dp[i + 1][j + 1] += dp[i][j];
					dp[i + 1][j + 1] %= mod;
					dp[i + 1][j - 1] %= mod;
				}
			}
		}

		long res = 0;
		for (int i = 0; i < 10; i++)
			res += dp[N][i];
		System.out.println(res % mod);
	}
}
