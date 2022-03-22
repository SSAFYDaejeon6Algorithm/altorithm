package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1890_jump {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			String[] line = br.readLine().split(" ");
			for(int j=0; j<N; j++) map[i][j] = Integer.parseInt(line[j]);
		}
		
		long[][] dp = new long[N][N];
		dp[0][0] = 1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for(int n=1; n<=9; n++) {
					if(i-n>=0 && map[i-n][j]==n) dp[i][j] += dp[i-n][j];
					if(j-n>=0 && map[i][j-n]==n) dp[i][j] += dp[i][j-n];
				}
			}
		}
		System.out.println(dp[N-1][N-1]);
	}
}
