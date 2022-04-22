package bj;

import java.io.*;
import java.util.*;

public class p_2206 {

	static int[][] map, visited, wall_visited;
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// input map
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String ip = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = ip.charAt(j) - '0';
			}
		}

		// BFS
		visited = new int[N][M]; // 2: not use drill, 1: use drill, 0: not visited
		
		Queue<Point> q = new LinkedList<>();
		visited[0][0] = 2;
		q.add(new Point(0, 0, 0, false));

		while (!q.isEmpty()) {
			Point p = q.poll();
			if (p.x == N - 1 && p.y == M - 1) {
				System.out.println(p.cost + 1);
				return;
			}

			// destination
			for (int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				// already break wall
				if (map[nx][ny] == 1 && p.isBreak)
					continue;
				// not yet
				if (map[nx][ny] == 1 && !p.isBreak) {
					visited[nx][ny] = 1;
					q.add(new Point(nx, ny, p.cost + 1, true));
					continue;
				}

				// visited
				if (visited[nx][ny] == 2)
					continue;
				if(visited[nx][ny] == 1 && p.isBreak)
					continue;

				if(p.isBreak)
					visited[nx][ny] = 1;
				else
					visited[nx][ny] = 2;
				q.add(new Point(nx, ny, p.cost + 1, p.isBreak));
			}
		}

		System.out.println(-1);
	}

	static class Point {
		int x, y, cost;
		boolean isBreak;

		Point(int x, int y, int cost, boolean isBreak) {
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.isBreak = isBreak;
		}
	}
}
