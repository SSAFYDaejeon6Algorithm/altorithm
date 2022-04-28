
public class B5582_공통부분문자열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		int l1 = s1.length();
		int l2 = s2.length();
		int dp[][] = new int[l1+1][l2+1];
		int ans = 0;
		
		for(int i=1; i<=l1; i++) { //i까지 고려했을 때
			for(int j=1; j<=l2; j++) { //j까지 고려했을 때
				if(s1.charAt(i-1) == s2.charAt(j-1)) dp[i][j] = dp[i-1][j-1]+1;
				
				ans = Math.max(ans, dp[i][j]);
			}
		}
		for(int i=0; i<=l1; i++) {
			for(int j=0; j<=l2; j++) System.out.print(dp[i][j] + " ");
			System.out.println();
		}
		
		System.out.println(ans);
	}
}
