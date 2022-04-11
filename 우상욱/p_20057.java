package bj;

import java.io.*;
import java.util.*;

public class p_20057 {

	static int map[][];
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { -1, 0, 1, 0 };

	static int sdx[][] = { { 0, -1, 1, -2, 2, -1, 1, -1, 1, 0 }, { 2, 1, 1, 0, 0, 0, 0, -1, -1, 1 }, { 0, -1, 1, 2, -2, 1, -1, -1, 1, 0 }, { -2, -1, -1, 0, 0, 0, 0, 1, 1, -1 } };
	static int sdy[][] = { { -2, -1, -1, 0, 0, 0, 0, 1, 1, -1 }, { 0, -1, 1, -2, 2, -1, 1, -1, 1, 0 }, { 2, 1, 1, 0, 0, 0, 0, -1, -1, 1 }, { 0, -1, 1, -2, 2, -1, 1, -1, 1, 0 } };
	static int spread_amount[] = { 5, 10, 10, 2, 2, 7, 7, 1, 1 };

	static int N, ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// map input
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		int x = N / 2;
		int y = N / 2;
		int d = -1;
		int n = 0;

		while (true) {
			d = (d + 1) % 4;
			if (d == 0 || d == 2)
				n++;

			for (int i = 0; i < n; i++) {
				x += dx[d];
				y += dy[d];

				if (!isInner(x, y)) {
					System.out.println(ans);
					return;
				}

				ans += sandStorm(x, y, d);
			}
		}
	}

	static int sandStorm(int x, int y, int tornado_dir) {
		int tot_out_sand = 0;
		int tot_spreaded_sand = 0;
		int sand = map[x][y];
		map[x][y] = 0;
		
		for(int d = 0; d < 10; d++) {
			int nx = x + sdx[tornado_dir][d];
			int ny = y + sdy[tornado_dir][d];
			
			int spreaded_sand;
			if(d == 9) {
				// rest sand
				spreaded_sand = sand - tot_spreaded_sand;
			} else {
				spreaded_sand = sand * spread_amount[d] / 100;
				tot_spreaded_sand += spreaded_sand;
			}
			
			// out of bound
			if(!isInner(nx, ny))
				tot_out_sand += spreaded_sand;
			else 
				map[nx][ny] += spreaded_sand;
		}
		
		return tot_out_sand;
	}

	static boolean isInner(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		else
			return true;
	}
}
