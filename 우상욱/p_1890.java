package bj;

import java.util.*;
import java.io.*;

public class p_1890 {

	static int[][] map;
	static long[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		dp = new long[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		
		dp[0][0] = 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(dp[i][j] == 0) continue;
				if(i == N -1 && j == N - 1) continue;
				int val = map[i][j];
				
				if(val + i < N)
					dp[val + i][j] += dp[i][j]; 
				if(val + j < N)
					dp[i][val + j] += dp[i][j];
			}
		}
		
		System.out.println(dp[N-1][N-1]);
	}
}
