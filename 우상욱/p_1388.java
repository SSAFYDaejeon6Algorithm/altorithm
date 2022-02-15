package bj;

import java.util.*;
import java.io.*;

public class p_1388 {

	static int map[][];
	static int ans;
	static int N, M;

	static int dx[] = { 0, 1 };
	static int dy[] = { 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// inputs
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				if (line.charAt(j) == '-')
					map[i][j] = 0;
				else
					map[i][j] = 1;
			}
		}

		// 0 : '-', 1 : '|'
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				countFloor(i, j);
	
		System.out.println(ans);
	}

	static void countFloor(int x, int y) {
		if(map[x][y] == -1)
			return;
		
		int floor = map[x][y];	// floor value
		map[x][y] = -1;			// visited
		ans++;
		
		for(int i = 1;;i++) {
			int nx = x + dx[floor] * i; 
			int ny = y + dy[floor] * i;
			
			if(0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] == floor)
				map[nx][ny] = -1;
			else
				return;
		}
	}
}
