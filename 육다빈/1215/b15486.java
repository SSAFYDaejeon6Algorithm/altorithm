package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G15486_leaveCompany {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] price = new int[N+1][2];
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			price[i][0] = Integer.parseInt(st.nextToken());
			price[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			dp[i] = Math.max(dp[i], dp[i-1]);
			
			int day = i+price[i][0]-1;
			if(day<=N) dp[day] = Math.max(dp[day], dp[i-1]+price[i][1]);
		}
		
		System.out.println(dp[N]);
	}

}
