package bj;

import java.io.*;
import java.util.*;

public class p_17070 {

	static int[][] map;
	static long[][][] dp;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		dp = new long[N][N][3]; // 0 : column, 1 : row, 2 : diagonal
		// map input
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		dp[0][1][0] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 2; j < N; j++) {
				if (map[i][j] == 1)
					continue;
				// diagonally
				if (i - 1 >= 0 && j - 1 >= 0)
					if (map[i - 1][j] == 0 && map[i][j - 1] == 0) // check wall
						dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];

				// row
				if (i - 1 >= 0)
					dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];

				// column
				dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
			}
		}

		System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
	}
}
