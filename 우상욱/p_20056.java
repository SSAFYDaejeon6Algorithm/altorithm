package bj;

import java.io.*;
import java.util.*;

public class p_20056 {

	static int N, M, K;
	static Fireball[][] map;
	static ArrayList<Fireball> fireballs;

	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new Fireball[N][N];
		fireballs = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			Fireball added = new Fireball(r, c, m, s, d);
			fireballs.add(added);

		}

		// command
		for(int i = 0; i < K; i++) {
			move();
			split();
		}
		
		// print result
		int ans = 0;
		for(int i = 0; i < fireballs.size(); i++)
			ans += fireballs.get(i).weight;
		System.out.println(ans);
	}

	static void split() {
		ArrayList<Fireball> tmp_fireballs = new ArrayList<>();

		// check need to split
		for (int i = 0; i < fireballs.size(); i++) {
			Fireball fb = fireballs.get(i);
			if (fb.cnt < 2)
				continue;

			for (int d = (fb.sameDir ? 0 : 1); d < 8; d += 2) {
				if(fb.weight / 5 == 0)
					break;
				
				tmp_fireballs.add(new Fireball(fb.x, fb.y, fb.weight / 5, fb.speed / fb.cnt, d));
			}
			
			fireballs.remove(i--);
		}
		
		// rest
		for(int i = 0; i < fireballs.size(); i++)
			tmp_fireballs.add(fireballs.get(i));
		fireballs = tmp_fireballs;
	}

	static void move() {
		map = new Fireball[N][N];

		for (int i = 0; i < fireballs.size(); i++) {
			Fireball fb = fireballs.get(i);
			int nx = fb.x + dx[fb.dir] * (fb.speed % N);
			int ny = fb.y + dy[fb.dir] * (fb.speed % N);
			
			nx = (nx < 0 ? nx + N : nx % N);
			ny = (ny < 0 ? ny + N : ny % N);

			fb.x = nx;
			fb.y = ny;
		
			if (map[nx][ny] == null)
				map[nx][ny] = fb;
			else {
				// combine
				map[nx][ny].weight += fb.weight;
				map[nx][ny].speed += fb.speed;
				map[nx][ny].cnt++;

				// check direction
				if (map[nx][ny].dir % 2 != fb.dir % 2)
					map[nx][ny].sameDir = false;

				fireballs.remove(i--);
			}
		}
	}

	static class Fireball {
		int x, y, weight, speed, dir, cnt;
		boolean sameDir;

		public Fireball(int x, int y, int weight, int speed, int dir) {
			this.x = x;
			this.y = y;
			this.weight = weight;
			this.speed = speed;
			this.dir = dir;
			cnt = 1;
			sameDir = true;
		}
	}
}
