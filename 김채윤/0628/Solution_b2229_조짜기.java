package vac;

import java.util.*;
import java.io.*;

public class Solution_b2229_조짜기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] arr = new int[N + 1];
		int[] dp = new int[N + 1];
		int RV = 0; //최적값
		int MAX = 0; //가장 높은 값
		dp[0] = 0; //자기자신
		dp[1] = 0; //자기자신

		for (int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();

			for (int j = i - 1; j >= 1; j--) {
				MAX = Math.max(MAX, Math.abs(arr[i] - arr[j]) + dp[j - 1]);
			}

			dp[i] = MAX;
			RV = dp[i];
		}
		System.out.println(RV);
	}
}