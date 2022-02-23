package bj;

import java.util.*;
import java.io.*;

public class p_9663 {

	static int map[][];
	static int N, ans;
	static int dx[] = {0, 0, 1, -1, 1, -1, -1, 1};
	static int dy[] = {1, -1, 0, 0, 1, -1, 1, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());		
		map = new int[N][N];

		chooseQueen(0);
			
		System.out.println(ans);
	}
	
	static void putQueen(int x, int y, boolean isRemove) {
		// x, y
		map[x][y] = (isRemove? map[x][y] - 1 : map[x][y] + 1);
		
		// 8 dir
		for(int d = 0; d < 8; d++) {
			for(int n = 1; n < N; n++) {
				int nx = x + dx[d] * n;
				int ny = y + dy[d] * n;
				
				if(0 <= nx && nx < N && 0 <= ny && ny < N)
					map[nx][ny] = (isRemove? map[nx][ny] - 1 : map[nx][ny] + 1);
			}
		}
	}
	
	static void chooseQueen(int x) {
		if(x == N) {
			ans++;
			return;
		}

		for(int j = 0; j < N; j++) {
			if(map[x][j] != 0)
				continue;
			
			putQueen(x, j, false);	// put
			chooseQueen(x + 1);
			putQueen(x, j, true);	// remove
		}
	}
}
