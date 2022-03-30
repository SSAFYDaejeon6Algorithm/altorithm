package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1520_내리막길 {
	
	static int R,C;
	static int m[][];
	static int ans = 0;
	static int d[][] = {{1,0},{0,1},{0,-1}};
	static boolean visit[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		m = new int[R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<C; j++) m[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<2; i++) {
			visit = new boolean[R][C];
			visit[0][0]=true;
			int nr = d[i][0];
			int nc = d[i][1];
			if(m[0][0] > m[nr][nc]) dfs(nr, nc);
		}
		
		System.out.println(ans);
	}
	
	static void dfs(int r, int c) {
		if(r==R-1 && c==C-1) {
			ans++;
			return;
		}
		
		visit[r][c] = true;
		
		for(int i=0; i<3; i++) {
			int nr = r + d[i][0];
			int nc = c + d[i][1];
			
			if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc] || m[nr][nc] >= m[r][c]) continue;
			
			dfs(nr, nc);
		}
		visit[r][c] = false;
	}
}
