package algorithm;

import java.io.*;
import java.util.*;

public class Solution_b17070_파이프옮기기1 {

	static int N;
	static int[][] map;
	static int ans = 0;
	
	static int[] dx = { 0, 1, 1 };
	static int[] dy = { 1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(1, 2, 0);
		System.out.println(ans);
	}

	public static void dfs(int x, int y, int d) {
		//조건
		if (x == N && y == N) {
			ans++;
		}

		for (int i = 0; i < 3; i++) {
			if (d == 0 && i == 1) {
				continue;
			}
			if (d == 1 && i == 0) {
				continue;
			}
			if (i == 2) { // 대각선으로 이동해야하는데 
				if (y + 1 <= N && x + 1 <= N) { //빈칸이 아닌 경우
					if (map[x][y + 1] != 0 || map[x + 1][y] != 0) {
						continue;
					}
				}
			}

			//다음 진행
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx > 0 && nx <= N && ny > 0 && ny <= N) {
				if (map[nx][ny] != 1) {
					dfs(nx, ny, i);
				}
			}
		}
	}
}