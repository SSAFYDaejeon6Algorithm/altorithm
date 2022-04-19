package bj;

import java.io.*;
import java.util.*;

public class p_20925 {

	static long dp[][];
	static int field_exp[][]; // 0: minimum exp, 1: exp per min
	static int move_time[][];
	static int N, T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		dp = new long[N + 1][T + 1];
		field_exp = new int[N + 1][2];
		move_time = new int[N + 1][N + 1];

		// input EXP
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			field_exp[i][0] = Integer.parseInt(st.nextToken());
			field_exp[i][1] = Integer.parseInt(st.nextToken());
		}

		// input time graph
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++)
				move_time[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int t = 1; t <= T; t++) {
			for (int i = 1; i <= N; i++) {
				long prev_exp = dp[i][t - 1];
				for (int j = 1; j <= N; j++) {
					if (i == j)
						continue;

					int prev_time = t - move_time[j][i];
					if (prev_time > 0)
						prev_exp = Math.max(prev_exp, dp[j][prev_time]);
				}

				// check minimum exp
				if (prev_exp < field_exp[i][0])
					continue;

				dp[i][t] = Math.max(prev_exp, (dp[i][t - 1] < field_exp[i][0]) ? 0 : dp[i][t - 1] + field_exp[i][1]);
			}
		}

		// print result
		long ans = 0;
		for (int i = 1; i <= N; i++)
			ans = Math.max(ans, dp[i][T]);
		System.out.println(ans);
	}

}
