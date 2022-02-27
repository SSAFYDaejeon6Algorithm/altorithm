package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2638 {
	
	static int R,C; //행, 열
	static int ans = 0; //정답
	static int m[][]; //맵 정보
	static int cheese = 0; //현재 남은 치즈 수
	static boolean visit[][]; //방문 여부
	static int d [][] = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken()); 
		m = new int[R][C];
		
		//맵 입력
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<C; j++) {
				m[i][j] = Integer.parseInt(st.nextToken());
				if(m[i][j]==1) cheese++; //치즈라면 치즈 수+1
			}
		}
		
		while(cheese != 0) bfs(); //치즈가 모두 사라질 때 까지
		
		System.out.println(ans);
	}
	
	static void bfs() {
		//배열 복사
		int copy [][] = new int[R][C]; 
		for(int i=0; i<R; i++) System.arraycopy(m[i], 0, copy[i], 0, C);
		
		visit = new boolean[R][C];
		visit[0][0] = true;
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(0,0));
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for(int i=0; i<4; i++) {
				int nr = cur.r + d[i][0];
				int nc = cur.c + d[i][1];
				
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc]) continue;
				
				if(copy[nr][nc] == 0) { //빈 칸이면 큐에 담아줌
					q.add(new Pos(nr,nc));
					visit[nr][nc] = true;
				}else ++copy[nr][nc]; //치즈 칸이라면 값 +1
			}
		}
		
		//사라질 치즈 제거
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(copy[i][j] >= 3) { //치즈가 2칸이상 인접했다면
					m[i][j] = 0;
					cheese--;
				}
			}
		}
		ans++;
	}
	
	static class Pos{
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
