package algorithm;

import java.util.Scanner;

public class Solution_b10844_쉬운계단수 {

	static long mod = 1000000000;
    
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		
		//N+1 은 자릿수이고, 10은 숫자 value 0~9를 뜻함.
		long[][] dp = new long[N + 1][10];
		
		// 첫 번째 자릿수 = 오른쪽 맨 끝의 자릿수이므로, 경우의 수가 1개밖에 없음.
		// 무조건 1저장.
		for(int i = 1; i < 10; i++) {
			dp[1][i] = 1; 
		}
		
		// 오른쪽에서 두 번째 자릿수 ~ 입력받은 N까지 탐색 
		for(int i = 2; i <= N; i++) {
			// i번째 자릿수의 자릿값들을 탐색 (0~9) 
			for(int j = 0; j < 10; j++) {
				// j=0, 즉 자릿값이 0이라면 이전 자릿수의 첫번째 자릿수만 가능 
				if(j == 0) {
					dp[i][0] = dp[i - 1][1] % mod;
				}
				// j=9라면 이전 자릿수는 8만 가능하므로 따라서 8로 지정해주기
				else if (j == 9) {
					dp[i][9] = dp[i - 1][8] % mod;
				}
				// 그 외의 경우 이전 자릿수의 자릿값 +1, -1 의 합이 됨 
				else {
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
				}
			}
		}
		
		long result = 0;
		
		// 각 자릿값마다의 경우의 수를 모두 더해준다. 
		for(int i = 0; i < 10; i++) {
			result += dp[N][i];
		}
		
		System.out.println(result % mod);
	}
 
}