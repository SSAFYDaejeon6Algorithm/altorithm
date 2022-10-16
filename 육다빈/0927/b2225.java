package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G2225_decompose {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long[][] dp = new long[K+1][N+1];
		
		for(int k=1; k<=K; k++) {
			for(int n=0; n<=N; n++) {
				if(k==1 || n==0) dp[k][n] = 1;
				else dp[k][n] = (dp[k][n-1] + dp[k-1][n])%1000000000;
			}
		}
		
		System.out.println(dp[K][N]);
	}

}
