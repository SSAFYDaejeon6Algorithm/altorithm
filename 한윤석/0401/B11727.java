public class B11727_2xn타일링2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int dp [] = new int[N+1];
		dp[1] = 1;
		
		for(int i=2; i<=N; i++) {
			if(i%2==0) dp[i] = (dp[i-1]*2+1)%10007;
			else dp[i] = (dp[i-1]*2-1)%10007;
		}
		
		System.out.println(dp[N]%10007);
	}
}
