package bj;

import java.io.*;
import java.util.*;

public class p_17142 {

	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };

	static ArrayList<Point> viruses;
	static int[][] map, cmap;
	static int N, M, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// map input
		map = new int[N][N];
		cmap = new int[N][N];
		viruses = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 2) {
					// virus
					viruses.add(new Point(i, j));
					map[i][j] = 0;
				} else if (map[i][j] == 1) {
					// wall
					map[i][j] = -1;
				}
			}
		}

		ans = Integer.MAX_VALUE;
		selectPoint(0, 0, 0);

		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans - 1);
	}

	static void selectPoint(int idx, int cnt, int selected) {
		if (cnt == M) {
			// copy map
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					cmap[i][j] = map[i][j];

			// check virus to start
			LinkedList<Point> q = new LinkedList<>();
			for (int i = 0; i < viruses.size(); i++) {
				Point v = viruses.get(i);

				if ((selected & 1 << i) == 1 << i) {
					// start with 1
					cmap[v.x][v.y] = 1;
					q.add(v);
				} else {
					// not activated virus
					cmap[v.x][v.y] = -2;
				}
			}

			// BFS
			int time = 2;
			while (!q.isEmpty()) {
				int qs = q.size();
				for (int i = 0; i < qs; i++) {
					Point p = q.poll();

					for (int d = 0; d < 4; d++) {
						int nx = p.x + dx[d];
						int ny = p.y + dy[d];

						if (nx < 0 || nx >= N || ny < 0 || ny >= N)
							continue;

						// activate virus
						if (cmap[nx][ny] == -2) {
							q.add(new Point(nx, ny));
							cmap[nx][ny] = -3;
						}
							
						if (cmap[nx][ny] != 0)
							continue;
						
						cmap[nx][ny] = time;
						q.add(new Point(nx, ny));
					}
				}

				time++;
			}

			// calculate max
			int tmp = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// not spread area found
					if (cmap[i][j] == 0)
						return;
					tmp = Math.max(tmp, cmap[i][j]);
				}
			}

			ans = Math.min(ans, tmp);
			return;
		}

		for (int i = idx; i < viruses.size(); i++)
			if ((selected & 1 << i) == 0)
				selectPoint(i + 1, cnt + 1, selected | 1 << i);
	}

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}