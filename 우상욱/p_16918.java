package bj;

import java.util.*;
import java.io.*;

public class p_16918 {

	static int map[][], tmp_map[][];
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		// map inputs
		map = new int[R][C];
		tmp_map = new int[R][C];
		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				if (line.charAt(c) == '.')
					map[r][c] = 0;
				else
					map[r][c] = 2;
			}
		}

		int cnt = 1;
		while (cnt++ != N) {
			// bomb count down
			for (int i = 0; i < R; i++)
				for (int j = 0; j < C; j++)
					map[i][j]--;
			
			// bomb explode
			for (int i = 0; i < R; i++)
				for (int j = 0; j < C; j++)
					tmp_map[i][j] = map[i][j];
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] != 0)
						continue;

					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];

						if (0 <= nx && nx < R && 0 <= ny && ny < C)
							tmp_map[nx][ny] = 0;
					}
				}
			}
			
			for (int i = 0; i < R; i++)
				for (int j = 0; j < C; j++)
					map[i][j] = tmp_map[i][j];

			// plant bomb
			if (cnt % 2 == 0) {
				for (int i = 0; i < R; i++)
					for (int j = 0; j < C; j++)
						if (map[i][j] <= 0)
							map[i][j] = 3;
			}
		}

		// print result
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0)
					sb.append('O');
				else
					sb.append('.');
			}
			sb.append('\n');
		}
		
		System.out.print(sb);
	}
}
