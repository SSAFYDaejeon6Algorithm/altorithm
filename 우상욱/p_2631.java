package bj;

import java.util.*;
import java.io.*;

public class p_2631 {

	static int[] nums, dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// number input
		nums = new int[N];
		for(int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(br.readLine());
		
		dp = new int[N];
		Arrays.fill(dp, 1);
		
		int ans = 1;
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < i; j++) {
				if(nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(N - ans);
	}
	
}
