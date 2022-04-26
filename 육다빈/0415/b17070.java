package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G17070_movePipe {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int[][][] dp = new int[N+1][N+1][3];
		dp[1][2][0] = 1; // 초기 시작 파이프
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=N; j++) {
				if( i==0 || j==0 || (i==1&&j==2) || map[i][j]==1) continue; //기본값 그대로
				else {
					dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];	// 가로
					dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];	// 세로
					if(map[i-1][j]!=1 && map[i][j-1]!=1) 			// 대각선
						dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
			}
		}
		System.out.println(dp[N][N][0]+dp[N][N][1]+dp[N][N][2]);
	}

}
