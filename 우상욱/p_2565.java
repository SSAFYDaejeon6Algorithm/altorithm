package bj;

import java.util.Scanner;

public class p_2565 {

	static int lines[] = new int[501];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		// inputs
		int start = 501, end = -1;
		for (int n = 0; n < N; n++) {
			int inp = sc.nextInt();
			start = Math.min(start, inp);
			end = Math.max(end, inp);

			lines[inp] = sc.nextInt();
		}
		
		// dp arr init
		int dp[] = new int[501];
		for (int i = start; i <= end; i++)
			dp[i] = (lines[i] == 0? 0 : 1);

		// solution
		for (int i = start + 1; i <= end; i++) {
			for (int j = start; j < i; j++)
				if (lines[i] > lines[j])
					dp[i] = Math.max(dp[i], dp[j] + 1);
		}

		int res = -1;
		for (int i = start; i <= end; i++)
			res = Math.max(res, dp[i]);

		System.out.println(N - res);
	}
}
