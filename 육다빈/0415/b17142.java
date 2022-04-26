package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G17142_lab3 {
	static int N, M, size, space=0, result=Integer.MAX_VALUE;
	static int[] list;
	static int[][] map;
	static List<Node> virus;
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
			Queue<Node> queue = new LinkedList<>();
			boolean[][] visit = new boolean[N][N];
			for(int idx : list) {
				Node now = virus.get(idx);
				queue.add(now);
				visit[now.i][now.j] = true;
			}
			int cnt=0, time=0;
			loop : while(!queue.isEmpty()) {
				if(++time==result) return;
				int len = queue.size();
				for(int i=0; i<len; i++) {
					Node now = queue.poll();
					for(int d=0; d<4; d++) {
						int ni = now.i + di[d];
						int nj = now.j + dj[d];
						if(ni<0 || ni>=N || nj<0 || nj>=N || visit[ni][nj] || map[ni][nj]==1) continue;
						queue.add(new Node(ni, nj));
						visit[ni][nj] = true;
						if(map[ni][nj]==0 && ++cnt==space) break loop;
					}
				}
			}
			if(space==cnt) result = Math.min(result, time);
			return;
		}
		for(int i=start; i<size; i++) {
			list[n] = i;
			comb(n+1, i+1);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		virus = new ArrayList<>();
		list = new int[M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) virus.add(new Node(i, j));
				else if(map[i][j]==0) space++;
			}
		}
		if(space==0) {
			System.out.println(0);
			return;
		}
		size = virus.size();
		comb(0, 0);
		System.out.println((result==Integer.MAX_VALUE)?-1:result);
	}

}
