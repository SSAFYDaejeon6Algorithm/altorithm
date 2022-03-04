package bj;

import java.util.*;
import java.io.*;

public class p_2638 {

	static int map[][], tmp_map[][];
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// map inputs
		map = new int[N][M];
		tmp_map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int days = 0;
		while(check_cheese()) {
			init_hollow();
			contact_air(0, 0);
			melt_cheese();
			days++;
		}
		
		System.out.println(days);
	}
	
	static void contact_air(int x, int y) {
		map[x][y] = -1; // air contacted
		
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			// out of bound
			if(nx < 0 || nx == N || ny < 0 || ny == M)
				continue;
			
			if(map[nx][ny] == 0)
				contact_air(nx, ny);
		}
	}
	
	static void init_hollow() {
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				if(map[i][j] < 0)
					map[i][j] = 0;
	}
	
	static boolean check_cheese() {
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				if(map[i][j] == 1)
					return true;
		return false;
	}
	
	static void melt_cheese() {
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				tmp_map[i][j] = map[i][j]; 
		
		for(int i = 0; i < N; i++) {
			for(int j = 1; j < M; j++) {
				if(map[i][j] != 1) continue;
				
				// check around
				int cnt = 0;
				for(int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if(nx < 0 || nx == N) continue;
					if(ny < 0 || ny == M) continue;
					if(map[nx][ny] == -1) cnt++;
				}
				
				if(cnt > 1)
					tmp_map[i][j] = 0;
			}
		}
		
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				map[i][j] = tmp_map[i][j]; 
	}
}
