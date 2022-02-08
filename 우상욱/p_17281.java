package bj;

import java.util.Scanner;

public class p_17281 {

	static int hits[][];
	static int order[];
	static boolean selected[];
	static int score, N, cur;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		// inputs
		hits = new int[N][9];
		for(int n = 0; n < N; n++)
			for(int m = 0; m < 9; m++)
				hits[n][m] = sc.nextInt();
		
		order = new int[9];
		selected = new boolean[9];
		
		// no1 to 4th hitter
		selected[0] = true;
		order[3] = 0;
		
		choose(0);
		
		System.out.println(score);
	}
	
	static int cal_score() {
		int ret = 0;
		cur = 0;
		
		for(int n = 0; n < N; n++) {
			int out_cnt = 0;
			int bases[] = new int[9];
			for(int i = 0; i < 9; i++)
				bases[i] = 0;
			
			// playing
			for(; out_cnt != 3; cur = (cur + 1) % 9) {
				int hitter_no = order[cur];
				int hit = hits[n][hitter_no]; 
				
				if(hit == 0)
					out_cnt++;
				else {
					// base advance
					for(int i = 0; i < 9; i++)
						if(bases[i] != 0)
							bases[i] += hit;
					bases[hitter_no] = hit;
					
					// gain score
					for(int i = 0; i < 9; i++) {
						if(bases[i] > 3) {
							bases[i] = 0;
							ret++;
						}
					}
				}
			} // end playing
		} // end for n
		
		return ret;
	}
	
	static void choose(int idx) {
		if(idx == 9) {
			score = Math.max(score, cal_score());
			return;
		}
		
		for(int i = 0; i < 9; i++) {
			if(selected[i])
				continue;
			
			selected[i] = true;
			order[idx] = i;
			
			if(idx == 2)
				// pass 4th
				choose(4);
			else
				choose(idx + 1);
			
			selected[i] = false;
		}	
	}
}
