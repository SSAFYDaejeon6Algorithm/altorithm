package bj;

import java.util.*;
import java.io.*;

public class p_3184 {

	static char map[][];

	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		// map inputs
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++)
				map[i][j] = line.charAt(j);
		}
		
		// search graph
		int wolf = 0, sheep = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == '#')
					continue;
				
				// count wolf and sheep
				int wolf_sheep[] = new int[2];
				Arrays.fill(wolf_sheep, 0);
				dfs(i, j, wolf_sheep);
				
				if(wolf_sheep[0] >= wolf_sheep[1])
					wolf += wolf_sheep[0];	// wolf win
				else
					sheep += wolf_sheep[1];	// sheep win
			}
		}
		
		// print result
		StringBuilder sb = new StringBuilder();
		sb.append(sheep).append(" ").append(wolf);
		System.out.println(sb);
	}

	static void dfs(int x, int y, int[] wolf_sheep) {
		if (map[x][y] == 'v')
			wolf_sheep[0]++;
		else if (map[x][y] == 'o')
			wolf_sheep[1]++;
		map[x][y] = '#'; // visit
		
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(0 <= nx && nx < map.length && 0 <= ny && ny < map[0].length && map[nx][ny] != '#')
				dfs(nx, ny, wolf_sheep);
		}
	}
}
