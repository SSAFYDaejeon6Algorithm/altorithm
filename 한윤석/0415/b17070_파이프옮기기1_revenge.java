package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17070_파이프옮기기1_2 {

	static int N;
	static int m[][];
	static int dp[][][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		m = new int[N][N];
		dp = new int[N][N][3];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) m[i][j] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][1][2] = 1;
		for(int i=0; i<N; i++) {
			for(int j=2; j<N; j++) {
				if(m[i][j] == 1) continue;
				
				dp[i][j][2] += dp[i][j-1][2] + dp[i][j-1][1];
				if(i>0) {
					dp[i][j][0] += dp[i-1][j][0] + dp[i-1][j][1];
					
					if(m[i-1][j] == 1 || m[i][j-1] == 1 || m[i-1][j-1] == 1) continue;
					
					dp[i][j][1] += dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
			}
		}
		
		System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
	}
}
