package bj;

import java.io.*;

public class p_11727 {

	static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		dp = new int[N + 1];
		dp[0] = dp[1] = 1;

		for (int i = 2; i <= N; i++)
			dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10007;
		System.out.println(dp[N]);
	}

}
