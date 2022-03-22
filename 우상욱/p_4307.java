package bj;

import java.io.*;
import java.util.*;

public class p_4307 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			// input position
			int fastest = -1;
			int slowest = -1;
			for(int i = 0; i < N; i++) {
				int inp = Integer.parseInt(br.readLine());
				
				fastest = Math.max(fastest, Math.min(L- inp, inp));
				slowest = Math.max(slowest, Math.max(L - inp, inp));
			}
			
			sb.append(fastest).append(" ").append(slowest).append("\n");
		}
		
		System.out.println(sb);
	}
}
