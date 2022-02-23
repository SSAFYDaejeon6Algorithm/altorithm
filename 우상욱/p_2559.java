package bj;

import java.util.*;
import java.io.*;

public class p_2559 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// inputs
		st = new StringTokenizer(br.readLine());
		int temperatures[] = new int[N];
		for(int i = 0; i < N; i++)
			temperatures[i] = Integer.parseInt(st.nextToken());
		
		// sum of first tempers
		int sum = 0;
		for(int i = 0; i < K; i++)
			sum += temperatures[i];
		
		int ans = sum;
		for(int i = 0; i < N - K; i++) {
			sum -= temperatures[i];
			sum += temperatures[i + K];
			ans = Math.max(ans, sum);
		}
		
		System.out.println(ans);
	}	
}
