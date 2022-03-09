package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//백준 1245 산봉우리 골드5
public class B1245 {

	static int R,C; //행, 열
	static int m [][]; //맵 정보
	static boolean visit[][]; //탐색 시작 후 방문했는지 여부
	static boolean check[][]; //산 봉우리 체크 여부
	static int ans = 0;
	static int d[][] = {{1,0},{-1,0},{0,1},{0,-1},{1,-1},{-1,1},{1,1},{-1,-1}};
	
	public static void main(String[] args) throws IOException {
		//변수초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		m = new int[R][C];
		check = new boolean[R][C];
		
		//맵 정보 입력
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<C; j++) m[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//이미 산 봉우리로 이어져있어서 탐색했던 칸이 아닐 때 bfs 탐색 시작
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) if(!check[i][j]) bfs(i,j);
		}
		
		System.out.println(ans);
	}
	
	static void bfs(int r, int c) {
		visit = new boolean[R][C];
		visit[r][c] = true; //이번 탐색에서 방문 체크
		check[r][c] = true; //산 봉우리 체크
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(r,c));
		boolean flag = true;
		
		while(!q.isEmpty()) {
			Pos current = q.poll();
			
			for(int i=0; i<8; i++) { //8방 탐색
				int nr = current.r + d[i][0];
				int nc = current.c + d[i][1];
				
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc]) continue;
				
				//같은 높이라면 같은 산봉우리이므로 산봉우리 체크 후 해당 칸도 탐색위해 큐에 담음 
				if(m[nr][nc] == m[current.r][current.c]) {
					visit[nr][nc] = true;
					check[nr][nc] = true;
					q.add(new Pos(nr,nc));
				}else if(m[nr][nc] > m[current.r][current.c]) { //자신보다 큰 높이 존재하면 산봉우리 안되므로 종료
					flag=false;
					break;
				}
			}
		}
		
		if(flag) ans++;
	}
	
	static class Pos{
		int r,c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
