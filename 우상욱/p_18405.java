package bj;

import java.io.*;
import java.util.*;

public class p_18405 {

	static int N, K, S, X, Y;
	static int map[][];
	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// input map
		PriorityQueue<Point> q = new PriorityQueue();
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] != 0)
					q.add(new Point(i, j, map[i][j]));
			}
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken()) - 1;
		Y = Integer.parseInt(st.nextToken()) - 1;
	
		// BFS
		int ans = 0;
		ArrayList<Point> tmp = new ArrayList<>();
		
		while(!q.isEmpty() && S-- != 0) {
			int qs = q.size(); 
			for(int s = 0; s < qs; s++) {
				Point p = q.poll();
				
				for(int d = 0; d < 4; d++) {
					int nx = p.x + dx[d];
					int ny = p.y + dy[d];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] != 0)
						continue;
					
					// meet destination
					if(nx == X && ny == Y) {
						System.out.println(p.n);
						return;
					}
					
					map[nx][ny] = p.n;
					tmp.add(new Point(nx, ny, p.n));
				}
			}
			
			q.addAll(tmp);
			tmp = new ArrayList<>();
		}
		
		System.out.println(map[X][Y]);
	}
	
	static class Point implements Comparable<Point> {
		int x, y, n;
		
		Point(int x, int y, int n) {
			this.x = x;
			this.y = y;
			this.n = n;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(n, o.n);
		}
	}
}
