package bj;

import java.io.*;
import java.util.*;

public class p_1520 {

	static int[][] map, dp;
	static boolean[][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int ans, N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// map input
		map = new int[N][M];
		dp = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// destination
		visited[N-1][M-1] = true;
		dp[N-1][M-1] = 1;
		
		System.out.println(dfs(0, 0));
	}

	static int dfs(int x, int y) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (nx < 0 || nx >= N || ny < 0 || ny >=M)
				continue;
			if (map[x][y] <= map[nx][ny])
				continue;
						
			if(visited[nx][ny])			// visited
				dp[x][y] += dp[nx][ny];
			else {						// not visited
				visited[nx][ny] = true;
				dp[x][y] += dfs(nx, ny);	
			}
		}
		
		return dp[x][y];
	}
}
