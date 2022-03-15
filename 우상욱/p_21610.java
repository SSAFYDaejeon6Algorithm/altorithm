import java.util.*;
import java.io.*;

public class p_21610 {

	static int dx[] = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int dy[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static boolean[][] prevClouds;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// input map
		int map[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		List<Point> curClouds = new ArrayList<>();
		curClouds.add(new Point(N - 1, 0));
		curClouds.add(new Point(N - 1, 1));
		curClouds.add(new Point(N - 2, 0));
		curClouds.add(new Point(N - 2, 1));

		// input command
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken()) - 1;
			int times = Integer.parseInt(st.nextToken()) % N;
			
			// move cloud
			for(int i = 0; i < curClouds.size(); i++) {
				int moved_x = curClouds.get(i).x + dx[dir] * times;
				int moved_y = curClouds.get(i).y + dy[dir] * times;
				
				curClouds.get(i).x = (moved_x < 0? moved_x + N: moved_x % N);
				curClouds.get(i).y = (moved_y < 0? moved_y + N: moved_y % N);
			}
			
			// rain
			prevClouds = new boolean[N][N];
			for(int i = 0; i < curClouds.size(); i++) {
				map[curClouds.get(i).x][curClouds.get(i).y]++;
				prevClouds[curClouds.get(i).x][curClouds.get(i).y] = true;
			}

			// Bug
			for(int i = 0; i < curClouds.size(); i++) {
				int cur_x = curClouds.get(i).x;
				int cur_y = curClouds.get(i).y;
				
				for(int d = 1; d < 8; d +=2) {
					int cross_x = cur_x + dx[d];
					int cross_y = cur_y + dy[d];
					
					if(cross_x < 0 || cross_x >= N || cross_y < 0 || cross_y >= N)
						continue;
					
					if(map[cross_x][cross_y] > 0)
						map[cur_x][cur_y]++;
				}
			}
			
			// remove current clouds
			curClouds = new ArrayList<>();
			
			// create other clouds
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] >= 2 && !prevClouds[i][j]) {
						map[i][j] -= 2;
						curClouds.add(new Point(i, j));
					}
				}
			}
		} // end command

		int ans = 0;
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				ans += map[i][j];
		System.out.println(ans);
	}
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
