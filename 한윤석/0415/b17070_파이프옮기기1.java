package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17070_파이프옮기기1 {

	static int N;
	static int m[][];
	static int dp[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		m = new int[N][N];
		dp = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) m[i][j] = Integer.parseInt(st.nextToken());
		}
		
		bfs();
		System.out.println(dp[N-1][N-1]);
	}
	
	static void bfs() {
		Queue<int []> q = new LinkedList<>();
		q.add(new int[] {0,1,3});
		
		while(!q.isEmpty()) {
			int [] pipe = q.poll();
			
			if(pipe[0] == N-1 && pipe[1] == N-1) continue;
			
			if(pipe[2] == 1) { // |
				if(isValid(pipe[0], pipe[1], 1)) {
					q.add(new int[] {pipe[0]+1, pipe[1], 1});
					dp[pipe[0]+1][pipe[1]]++;
				}
				if(isValid(pipe[0], pipe[1], 2)) {
					q.add(new int[] {pipe[0]+1, pipe[1]+1, 2});
					dp[pipe[0]+1][pipe[1]+1]++;
				}
			}else if(pipe[2] == 2) { // \
				if(isValid(pipe[0], pipe[1], 1)) {
					q.add(new int[] {pipe[0]+1, pipe[1], 1});
					dp[pipe[0]+1][pipe[1]]++;
				}
				if(isValid(pipe[0], pipe[1], 2)) {
					q.add(new int[] {pipe[0]+1, pipe[1]+1, 2});
					dp[pipe[0]+1][pipe[1]+1]++;
				}
				if(isValid(pipe[0], pipe[1], 3)) {
					q.add(new int[] {pipe[0], pipe[1]+1, 3});
					dp[pipe[0]][pipe[1]+1]++;
				}
			}else if(pipe[2] == 3) { // ㅡ
				if(isValid(pipe[0], pipe[1], 2)) {
					q.add(new int[] {pipe[0]+1, pipe[1]+1, 2});
					dp[pipe[0]+1][pipe[1]+1]++;
				}
				if(isValid(pipe[0], pipe[1], 3)) {
					q.add(new int[] {pipe[0], pipe[1]+1, 3});
					dp[pipe[0]][pipe[1]+1]++;
				}
			}
		}
	}
	
	static boolean isValid(int r, int c, int type) {
		int nr = type <= 2 ? r+1 : r;
		int nc = type >= 2 ? c+1 : c;
		
		if(nr < 0 || nr >= N || nc < 0 || nc >= N) return false;
		if(type % 2 == 1) if(m[nr][nc] == 1) return false;
		if(type == 2) {
			if(m[nr][c] == 1 || m[r][nc] == 1 || m[nr][nc] == 1) return false;
		}
		
		return true;
	}
}
