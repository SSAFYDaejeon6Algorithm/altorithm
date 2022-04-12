package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_b20056_마법사상어와파이어볼 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, m, k, ans;
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static Queue<Fb>[][] map;

	static class Fb {
		int m, s, d;

		public Fb(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new LinkedList[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = new LinkedList<Fb>();
			}
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new Fb(m, s, d));
		}
		for (int time = 0; time < k; time++) {
			moveFB();
			splitFB();
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (Fb fb : map[i][j]) {
					ans += fb.m;
				}
			}
		}
		System.out.println(ans);
	}

	private static void moveFB() {
		Queue<Fb>[][] Mmap = new LinkedList[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Mmap[i][j] = new LinkedList<Fb>();
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j].isEmpty())
					continue;
				// if exsit
				while (!map[i][j].isEmpty()) {
					Fb cur = map[i][j].poll();
					int nr = (i + dy[cur.d] * cur.s) % n;
					int nc = (j + dx[cur.d] * cur.s) % n;
					nr = (nr < 0) ? nr + n : nr;
					nc = (nc < 0) ? nc + n : nc;
					
					Mmap[nr][nc].add(new Fb(cur.m, cur.s, cur.d));
				}
			}
		}
		map = Mmap;
	}

	private static void splitFB() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j].size() <= 1)
					continue;
				int dOdd = 0, dEven = 0, mSum = 0, sSum = 0;
				int size = map[i][j].size();
				while (!map[i][j].isEmpty()) {
					Fb cur = map[i][j].poll();
					if (cur.d % 2 == 1) {
						dOdd++;
					} else {
						dEven++;
					}
					mSum += cur.m;
					sSum += cur.s;
				}
				int nm = mSum / 5;
				int ns = sSum / size;
				if (nm != 0) {
					if (dOdd == 0 || dEven == 0) {
						map[i][j].add(new Fb(nm, ns, 0));
						map[i][j].add(new Fb(nm, ns, 2));
						map[i][j].add(new Fb(nm, ns, 4));
						map[i][j].add(new Fb(nm, ns, 6));
					} else {
						map[i][j].add(new Fb(nm, ns, 1));
						map[i][j].add(new Fb(nm, ns, 3));
						map[i][j].add(new Fb(nm, ns, 5));
						map[i][j].add(new Fb(nm, ns, 7));
					}
				}
			}
		}
	}
}