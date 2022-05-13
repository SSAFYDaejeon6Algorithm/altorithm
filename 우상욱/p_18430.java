package bj;

import java.io.*;
import java.util.*;

public class p_18430 {

	static int N, M, ans;
	static int[][] wood;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// input wood strength
		wood = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				wood[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		makeBoom(0, 0, 0);
		
		System.out.println(ans);
	}
	
	static void makeBoom(int cnt, int sum, int visited) {
		if(cnt == N * M) {
			ans = Math.max(ans, sum);
			return;
		}
		int x = cnt / M;
		int y = cnt % M;
		
		for(int d = 0; d < 4; d++) {
			if((visited & 1 << cnt) == 1 << cnt)
				break;
			int nx1 = x + dx[d], ny1 = y + dy[d];
			int nx2 = x + dx[(d + 1) % 4], ny2 = y + dy[(d + 1) % 4];
			
			// bound check
			if(nx1 < 0 || ny1 < 0 || nx1 >= N || ny1 >= M)
				continue;
			if(nx2 < 0 || ny2 < 0 || nx2 >= N || ny2 >= M)
				continue;
			
			// visited check
			int pos = nx1 * M + ny1;
			if((visited & 1 << pos) == 1 << pos)
				continue;
			pos = nx2 * M + ny2;
			if((visited & 1 << pos) == 1 << pos)
				continue;
			
			// visit
			int next_v = visited | 1 << (nx1 * M + ny1);
			next_v |= 1 << (nx2 * M + ny2);
			next_v |= 1 << cnt;
				
			makeBoom(cnt + 1, sum + wood[nx1][ny1] + wood[nx2][ny2] + wood[x][y] * 2, next_v);
		}
		
		makeBoom(cnt + 1, sum, visited);
	}
}
