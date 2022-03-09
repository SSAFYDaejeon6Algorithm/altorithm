package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//백준 16918 봄버맨 실버1
public class B16918 {
	
	static int R,C,N; //행, 열, N초 후
	static int m [][]; //맵 정보 0:빈칸 1>= 폭탄
	static int d [][] = {{1,0},{0,1},{-1,0},{0,-1}};

	public static void main(String[] args) throws IOException {
		//변수 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		m = new int[R][C];
		int now = 0; //현재까지 소요 시간
		
		//맵 정보 입력
		for(int i=0; i<R; i++) {
			String input = br.readLine();
			for(int j=0; j<C; j++) m[i][j] = input.charAt(j) == '.' ? 0 : 1;
		}
		
		while(now++ != N) {
			if(now == 1) continue; //첫 1초는 건너뜀
			setBombTime(); //폭탄 타이머 증가
			if(now%2 == 0) setNewBomb(); //빈칸에 폭탄 설치
			else if(now%2 == 1) explosion(); //3초 지난 폭탄 폭발
		}
		
		//결과 출력
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(m[i][j]>=1)System.out.print('O');
				else System.out.print('.');
			}
			System.out.println();
		}
	}
	
	static void setBombTime() { //폭탄 타이머 증가
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) if(m[i][j] >= 1) m[i][j]++;
		}
	}
	
	static void setNewBomb() { //빈칸에 폭탄 설치
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) if(m[i][j] == 0) m[i][j]++;
		}
	}
	
	static void explosion() { //3초 지난 폭탄 폭발
		Queue<Pos> q = new LinkedList<>();
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(m[i][j] >= 3) q.add(new Pos(i,j));
			}
		}
		
		while(!q.isEmpty()) {
			Pos current = q.poll();
			m[current.r][current.c] = 0;
			for(int i=0; i<4; i++) {
				int nr = current.r + d[i][0];
				int nc = current.c + d[i][1];
				
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || m[nr][nc] == 0) continue;
				m[nr][nc] = 0;
			}
		}
	}
	
	static class Pos{
		int r,c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
