package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class G1245_farmManagement {
	
	static final int peek = 501;	// 탐색한 산봉우리 체크용 (산봉우리 최대 높이=500)
	static int N, M, cnt = 0;
	static int[][] map;
	static boolean[][] visit;
	static int[] di = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dj = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	static class Node{
		int i;
		int j;
		Node(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	// 해당 격자에서 bfs로 팔방탐색
	static void bfs(int i, int j) {
		List<Node> list = new ArrayList<>();
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(i, j));
		visit[i][j] = true;
		
		loop : while(!queue.isEmpty()) {
			Node now = queue.poll();
			list.add(now);
			for(int d=0; d<8; d++) {
				int ni = now.i + di[d];
				int nj = now.j + dj[d];
				if(ni<0 || ni>=N || nj<0 || nj>=M || visit[ni][nj]) continue;
				if(map[ni][nj] > map[now.i][now.j]) {	// 더 높은 곳 만나면 산봉우리 리스트 비우고 리턴
					list.clear();
					break loop;
				}
				else if(map[ni][nj] == map[now.i][now.j]) queue.add(new Node(ni, nj));
				visit[ni][nj] = true;
			}
		}
		if(!list.isEmpty()) {	// 탐색한 산봉우리 표시해두기
			cnt++;
			for(Node no: list) map[no.i][no.j] = peek;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			line = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == peek || map[i][j] == 0) continue;	// 이미 탐색한 산봉우리나, 평지 제외
				visit = new boolean[N][M];
				bfs(i, j);	// 산봉우리 탐색
			}
		}
		System.out.println(cnt);
	}

}
