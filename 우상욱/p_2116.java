package bj;

import java.util.*;
import java.io.*;

public class p_2116 {

	static int dices[][];
	static int opposite_side[] = { 5, 3, 4, 1, 2, 0 }; // A - F, B - D, C - E

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// dice inputs
		dices = new int[N][6];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 6; j++)
				dices[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// first dice is free to position
		int ans = 0;
		for(int n = 0; n < 6; n++)
			ans = Math.max(ans, stack_dice(dices[0][n], 0));
		
		System.out.println(ans);
	}
	
	static int stack_dice(int prev_top, int cnt) {
		// escape condition
		if(cnt == dices.length)
			return 0;
		
		int down_side_val = prev_top, down_side_idx = -1;
		int top_side_idx = -1, top_side_val = -1;
		
		// find down side index
		for(int i = 0; i < 6; i++)
			if(prev_top == dices[cnt][i])
				down_side_idx = i;
		
		// find top side index and value
		top_side_idx = opposite_side[down_side_idx];
		top_side_val = dices[cnt][top_side_idx];
		
		return getMaxSide(down_side_val, top_side_val ,dices[cnt]) + stack_dice(top_side_val, cnt + 1);
	}

	static int getMaxSide(int down_side_val, int top_side_val, int dice[]) {
		int ret_max = 0;
		
		// find max
		for(int i = 0; i < 6; i++)
			if(down_side_val != dice[i] && top_side_val != dice[i])
				ret_max = Math.max(ret_max, dice[i]);
				
		return ret_max;
	}
}
