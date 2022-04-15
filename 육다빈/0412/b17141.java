package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G17141_lab2 {
	static int N, M, size, space=0, result=Integer.MAX_VALUE;
	static int[][] map;
	static int[] list;
	static List<Node> virus = new ArrayList<>();
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	static class Node{
		int i, j;
		Node(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	static void comb(int n, int start) {
		if(n==M) {
			spread();
			return;
		}
		for(int i=start; i<size; i++) {
			list[n] = i;
			comb(n+1, i+1);
		}
	}
	static void spread() {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		for(int i : list) {
			Node now = virus.get(i);
			queue.add(now);
			visit[now.i][now.j] = true;
		}
		int time = 0;
		int cnt = M;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int q=0; q<size; q++) {
				Node now = queue.poll();
				for(int d=0; d<4; d++) {
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					if(ni<0 || ni>=N || nj<0 || nj>=N || map[ni][nj]==1 || visit[ni][nj]) continue;
					cnt++;
					visit[ni][nj] = true;
					queue.add(new Node(ni, nj));
				}
			}
			if(++time>=result) return;
			if(cnt==space) break;
		}
		if(cnt==space) result = time;
	}
  
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		list = new int[M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) virus.add(new Node(i, j));
				if(map[i][j]!=1) space++;
			}
		}
		if(space==M) {
			System.out.println(0);
			return;
		}
		size = virus.size();
		comb(0, 0);
		System.out.println((result==Integer.MAX_VALUE)?-1:result);
	}

}
