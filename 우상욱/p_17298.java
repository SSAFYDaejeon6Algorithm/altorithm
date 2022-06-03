package bj;

import java.io.*;
import java.util.*;

public class p_17298 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int ans[] = new int[N];
		Arrays.fill(ans, -1);
		
		Stack<Num> stk = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			int inp = Integer.parseInt(st.nextToken());
						
			while(true) {
				if(stk.isEmpty() || stk.peek().val >= inp) {
					stk.push(new Num(i, inp));
					break;
				}
				
				ans[stk.pop().idx] = inp;
			}
		}
		
		// print result
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++)
			sb.append(ans[i]).append(" ");
		System.out.println(sb);
	}
	
	static class Num {
		int idx, val;
		public Num(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
	}
}
