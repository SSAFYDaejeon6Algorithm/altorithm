package bj;

import java.util.Scanner;

public class p_10870 {

	static int dp[] = new int[21];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print(fibonacci(sc.nextInt()));
	}

	static int fibonacci(int n) {
		dp[0] = 0;
		dp[1] = 1;

		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		return dp[n];
	}
}
