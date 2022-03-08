package bj;

import java.util.*;
import java.io.*;

public class p_1245 {

	static int map[][], visited[][];
	static int dx[] = { 0, 0, -1, 1, 1, 1, -1, -1 };
	static int dy[] = { -1, 1, 0, 0, 1, -1, 1, -1 };
	static int ans;
	static boolean isPeak;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// map input
		map = new int[N][M];
		visited = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] != 0)
					continue;

				// not visited
				isPeak = true;
				dfs(i, j);

				if (isPeak)
					ans++;
			}
		}

		System.out.println(ans);
	}

	static void dfs(int x, int y) {
		visited[x][y] = -1;

		for (int d = 0; d < 8; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (0 <= nx && nx < map.length && 0 <= ny && ny < map[0].length) {
				// higher peek is there ~!
				if (map[x][y] < map[nx][ny])
					isPeak = false;

				// not visited and same height
				if (visited[nx][ny] == 0 && map[x][y] == map[nx][ny]) {
					dfs(nx, ny);
				}
			}

		}
	}
}
