package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2229_조짜기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr [] = new int[N];
		int dp [] = new int[N]; //0부터 i까지 고려했을 때 최대값
		String [] input = br.readLine().split(" ");
		
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(input[i]);
		
		for(int i=0; i<N; i++) { //앞에서부터 탐색
			int min = arr[i]; // j부터 i까지의 최소
			int max = arr[i]; // j부터 i까지의 최대
			
			for(int j=i; j>=0; j--) { //i번부터 뒤로 탐색
				min = Math.min(min, arr[j]);
				max = Math.max(max, arr[j]);
				
				// j부터 i까지의 최대 차 + 직전까지의 합
				if(j==0) dp[i] = Math.max(dp[i], max-min);
				else dp[i] = Math.max(dp[i],  max - min + dp[j-1]); 
			}
		}
		
		System.out.println(dp[N-1]);
	}
}
