package bj;

import java.io.*;
import java.util.*;

public class p_2229 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		// input scores
		int scores[] = new int[N + 1];
		for (int i = 1; i <= N; i++)
			scores[i] = Integer.parseInt(st.nextToken());

		// max score until i index
		int dp[] = new int[N + 1];
		dp[0] = 0;

		int min, max;
		for (int i = 1; i <= N; i++) {
			max = min = scores[i];

			for (int j = i; j >= 1; j--) {
				max = Math.max(max, scores[j]);
				min = Math.min(min, scores[j]);
				dp[i] = Math.max(dp[i], max - min + dp[j - 1]);
			}
		}

		System.out.println(dp[N]);
	}
}
