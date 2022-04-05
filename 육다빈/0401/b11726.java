package silver;

import java.util.Scanner;

public class S11726_2Ntiling {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] dp = new int[N];
		dp[0] = 1;
		if(N>1) dp[1] = 2;
		for(int n=2; n<N; n++) dp[n] = (dp[n-1] + dp[n-2])%10007;
		System.out.println(dp[N-1]);
	}

}
