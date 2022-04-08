package silver;

import java.util.Arrays;
import java.util.Scanner;

public class S10844_easyStairNumber {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		long[][] dp = new long[2][10];
		Arrays.fill(dp[0], 1);
		dp[0][0] = 0;
		
		int idx = 0;
		for(int n=1; n<N; n++) {
			int now = (idx+1)%2; 	// 이번에 채울 배열의 인덱스
			for(int i=0; i<10; i++) {
				dp[now][i] = (i-1>=0 ? dp[idx][i-1] : 0) + (i+1<10 ? dp[idx][i+1] : 0)%1000000000;
			}
			idx = now;
		}
		long result = 0;
		for(int i=0; i<10; i++) result = (result + dp[idx][i])%1000000000;
		System.out.println(result);
	}

}
