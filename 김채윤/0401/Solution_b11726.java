package algorithm;

import java.io.*;

public class Solution_b11726 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n+1];
        
		dp[0] =1;
		dp[1]= 1;  
        
		for(int i=2; i<n+1; i++) {
			dp[i] = (dp[i-1] + dp[i-2])%10007;
		}
		System.out.println(dp[n]);
	}

}


