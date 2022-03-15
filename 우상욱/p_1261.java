package bj;

import java.util.*;
import java.io.*;

public class p_1261 {
	
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M], dist = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
				dist[i][j] = Integer.MAX_VALUE;
			}
		}

		Queue<Point> q = new LinkedList<>();

		// start point
		q.add(new Point(0, 0));
		dist[0][0] = 0;

		while (!q.isEmpty()) {
			Point tmp = q.poll();
			int x = tmp.x, y = tmp.y;
			
			for(int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				// out of bound
				if(nx < 0 || nx >= N) continue;
				if(ny < 0 || ny >= M) continue;
				
				// update distance
				if(dist[x][y] + map[nx][ny] < dist[nx][ny]) {
					dist[nx][ny] = dist[x][y] + map[nx][ny];
					q.add(new Point(nx, ny));
				}
			}
		}

		System.out.println(dist[N - 1][M - 1]);
	}

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
