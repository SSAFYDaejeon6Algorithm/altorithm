package bj;

import java.util.*;
import java.io.*;

public class p_1347 {

	static char[][] map;
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		map = new char[111][111];
		for(int i = 0; i < map.length; i++)
			Arrays.fill(map[i], '#');
		
		int x = 55, y = 55;
		int dir = 0; // 0 : South, 1 : east, 2: north, 3:west
		map[x][y] = '.';
		
		String written_note = br.readLine();
		for(int n = 0; n < N; n++) {
			if(written_note.charAt(n) == 'L')
				dir = turn(dir, true);
			else if(written_note.charAt(n) == 'R')
				dir = turn(dir, false);
			else {
				x += dx[dir];
				y += dy[dir];
				
				map[x][y] = '.';
			}
		}
		
		int left_x = Integer.MAX_VALUE, left_y = Integer.MAX_VALUE;
		int right_x = 0, right_y = 0;
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				if(map[i][j] == '.') {
					left_x = Math.min(left_x, i);
					left_y = Math.min(left_y, j);
					right_x = Math.max(right_x, i);
					right_y = Math.max(right_y, j);
				}
			}
		}
		
		for(int i = left_x; i <= right_x; i++) {
			for(int j = left_y; j <= right_y; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	static int turn(int cur_dir, boolean isRight) {
		// true : right, false : left

		if (isRight)
			return (cur_dir + 1) % 4;
		else
			return (cur_dir - 1 < 0) ? 3 : cur_dir - 1;
	}
}
