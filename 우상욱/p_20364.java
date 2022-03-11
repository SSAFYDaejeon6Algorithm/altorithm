package bj;

import java.util.*;
import java.io.*;

public class p_20364 {

	static int qq_world[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		// node inputed
		qq_world = new int[N + 1];
		StringBuilder sb = new StringBuilder();
		
		for(int q = 0; q < Q; q++) {
			int wanted = Integer.parseInt(br.readLine());
			
			int result = findRoot(wanted, -1);
			if(result == -1) {
				// can reserve
				qq_world[wanted] = -1; // reserve land
				sb.append(0).append("\n");
			} else
				// can not reserve
				sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	static int findRoot(int num, int last_visited) {		
		// meet reserved land
		if(qq_world[num] != 0)
			last_visited = num;
		
		if(num == 1)
			return last_visited;
		else
			return findRoot(num / 2, last_visited);
	}
}
