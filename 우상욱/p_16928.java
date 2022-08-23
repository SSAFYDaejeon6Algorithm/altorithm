package bj;

import java.util.*;
import java.io.*;

public class p_16928 {

	static int ladder[] = new int[101];
	static boolean visited[] = new boolean[101];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// input ladders and snakes
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()); 
		for(int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			ladder[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		
		while(!q.isEmpty()) {
			int q_size = q.size();
			
			for(int s = 0; s < q_size; s++) {
				int popped = q.poll();
				if(popped == 100) {
					System.out.println(ans);
					return;
				}
				
				// roll dice
				for(int i = 1; i <= 6; i++) {
					int next_pos = popped + i;
					if(next_pos > 100)
						continue;
					
					// has ladder or snake
					if(ladder[next_pos] != 0)
						next_pos = ladder[next_pos];
						
					if(visited[next_pos])
						continue;
					
					visited[next_pos] = true;
					q.add(next_pos);										
				}
			}
			ans++;
		}
	}
}
