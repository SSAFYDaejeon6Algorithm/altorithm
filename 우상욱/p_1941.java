package bj;

import java.util.*;
import java.io.*;

public class p_1941 {

	static int classroom[][];
	static boolean arr_visit[][];
	static int ans;

	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// map inputs
		classroom = new int[5][5];
		arr_visit = new boolean[5][5];
		for (int i = 0; i < 5; i++) {
			String line = br.readLine();
			for (int j = 0; j < 5; j++)
				// 'Y' : 1, 'S' : 0
				classroom[i][j] = (line.charAt(j) == 'Y' ? 1 : 0);
		}

		comb(0, 0, 0, 0);

		System.out.println(ans);
	}

	static void comb(int visited, int idx, int cnt, int sum) {
		if (sum >= 4)
			return;
		if (cnt == 7) {
			// create 2 dimension visited array
			for (int i = 0; i < 5; i++)
				Arrays.fill(arr_visit[i], false);

			for (int i = 0; i < 25; i++)
				if ((visited & 1 << i) == 1 << i)
					arr_visit[i / 5][i % 5] = true;

			if (isConnect(arr_visit))
				ans++;
			return;
		}
		if (idx == 25)
			return;

		comb(visited | (1 << idx), idx + 1, cnt + 1, sum + classroom[idx / 5][idx % 5]);
		comb(visited, idx + 1, cnt, sum);
	}

	static boolean isConnect(boolean[][] visited) {
		Queue<int[]> q = new LinkedList<int[]>();
		// find start
		for (int i = 0; i < 25; i++) {
			if (visited[i / 5][i % 5]) {
				q.add(new int[] { i / 5, i % 5 });
				visited[i / 5][i % 5] = false;
				break;
			}
		}
		
		int cnt = 0;
		while (!q.isEmpty()) {
			int[] popped = q.poll();
			int x = popped[0];
			int y = popped[1];

			cnt++;

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5)
					continue;
				if (!visited[nx][ny])
					continue;

				q.add(new int[] { nx, ny });
				visited[nx][ny] = false;
			}
		}

		// all connected
		if (cnt == 7)
			return true;
		else
			return false;
	}
}
