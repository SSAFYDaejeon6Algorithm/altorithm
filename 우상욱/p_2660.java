package bj;

import java.io.*;
import java.util.*;

public class p_2660 {
	static final int INF = 100000;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] members = new int[N + 1][N + 1];
		for(int i = 0; i <= N; i++)
			for(int j = 0; j <= N; j++) 
				if(i != j)
					members[i][j] = INF;
		
		
		// friends info inputed
		int from = 0, to = 0;
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			// escape condition
			if(from == -1 && to == -1) break;
				
			members[from][to] = 1;
			members[to][from] = 1;
		}
		
		// calculate min distance from each other
		for(int j = 1; j <= N; j++) {
			for(int i = 1; i <= N; i++) {
				for(int k = 1; k <= N; k++) {
					if(members[i][k] > members[i][j] + members[j][k]) {
						members[i][k] = members[i][j] + members[j][k]; 
					}
				}
			}
		}
		
		int min_score = INF;
		int[] scores = new int[N + 1];
		Arrays.fill(scores, 0);
		
		// count minimum
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++)
				scores[i] = Math.max(scores[i], members[i][j]);
			
			min_score = Math.min(min_score, scores[i]);
		}
		
		
		// find min_score member
		ArrayList<Integer> ans = new ArrayList<>();
		for(int i = 1; i <= N; i++)
			if(scores[i] == min_score)
				ans.add(i);
		
		// print result
		StringBuilder sb = new StringBuilder();
		sb.append(min_score).append(" ").append(ans.size()).append("\n");
		for(int i = 0; i < ans.size(); i++)
			sb.append(ans.get(i)).append(" ");
		
		System.out.println(sb);
	}
}
