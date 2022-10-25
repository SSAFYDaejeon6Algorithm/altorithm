public class B1309_동물원 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int dp [][] = new int[N+1][3];
		Arrays.fill(dp[1], 1); //처음엔 모든 경우가 한 가지
		
		for(int i=2; i<=N; i++) {
			dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
			dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % 9901;
			dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 9901;
		}
		System.out.println((dp[N][0] + dp[N][1] + dp[N][2]) % 9901);
	}

}
