public class B10844_쉬운계단수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long dp [][] = new long[N+1][10]; //자릿수가 i 이고 뒷 자리가 j인 경우
		long ans = 0;
		long mod = 1000000000;
		
		for(int i=1; i<10; i++) dp[1][i] = 1;
		
		for(int i=2; i<=N; i++) {
			for(int j=0; j<10; j++) {
				if(j==0) {
					dp[i][j] = dp[i-1][1] % mod;
				}else if(j==9) {
					dp[i][j] = dp[i-1][8] % mod;
				}else {
					dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % mod;
				}
			}
		}
		for(int i=0; i<10; i++) ans += dp[N][i];
		
		System.out.println(ans%mod);
	}
}
