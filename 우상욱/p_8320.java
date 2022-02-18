package bj;

import java.util.*;
import java.io.*;

public class p_8320 {

	static int ans[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		ans = new int[N+1];
		for(int n = 1; n <= N; n++) {
			// calculate n's divisor
			int cnt = 0;
			for(int i = 1; i <= Math.sqrt(n); i++)
				if(n % i == 0)
					cnt++;
			
			ans[n] = ans[n-1] + cnt;
		}
		
		System.out.println(ans[N]);
	}
}
