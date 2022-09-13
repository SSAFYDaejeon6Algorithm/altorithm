package bj;

import java.util.*;
import java.io.*;

public class p_1826 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// gas station info
		int N = Integer.parseInt(br.readLine());
		int gas_stations[][] = new int[N+1][2]; // 0: position, 1: fuel
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); 
			gas_stations[i][0] = Integer.parseInt(st.nextToken());
			gas_stations[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// sort with position
		Arrays.sort(gas_stations, new Comparator<int[]>() {
		    @Override
			public int compare(int[] o1, int[] o2) {
		    	 return Integer.compare(o1[0], o2[0]);
	           }
	        });

		// start spot
		st = new StringTokenizer(br.readLine());
		int distance = Integer.parseInt(st.nextToken());
		int fuel = Integer.parseInt(st.nextToken());
		
		// for checking gas station that can go most far from current position
		PriorityQueue<int[]> pq = new PriorityQueue<>(N, new Comparator<int[]>() {
		    @Override
			public int compare(int[] o1, int[] o2) {
		    	 return Integer.compare(o2[1], o1[1]);
	           }
	        });
		
		int cur_pos = fuel, i = 1;
		int visit_cnt = 0; // answer
		
		while(cur_pos < distance) {
			for(;i <= N; i++) {
				if(gas_stations[i][0] <= cur_pos)
					pq.add(gas_stations[i]);
				else
					break;
			}
			
			if(pq.isEmpty()) {
				// can not reach
				System.out.println(-1);
				return;
			} else {
				int[] tmp = pq.poll();
				cur_pos += tmp[1];
				visit_cnt++;
			}
		}
		
		System.out.println(visit_cnt);
	}

}
