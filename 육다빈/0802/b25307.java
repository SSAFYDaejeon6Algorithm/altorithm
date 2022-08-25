package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class B25307_browseDepartmentStore {
	static int N, M, K;
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	static int[][] map;
	static List<Spot> manekins = new ArrayList<Spot>();
	
	static class Spot{
		int i, j;
		Spot(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	static int bfs(Spot start, int limit, boolean isSiru) {
		Queue<Spot> queue = new LinkedList<Spot>();
		queue.add(start);
		map[start.i][start.j] = 3;
		int time = 0;
		while(!queue.isEmpty()) {
			time++;
			int size = queue.size();
			for(int s=0; s<size; s++) {
				Spot now = queue.poll();
				for(int d=0; d<4; d++) {
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
					if(isSiru) {
						if(map[ni][nj]==2) return time;
						else if(map[ni][nj]==0) {
							queue.add(new Spot(ni, nj));
							map[ni][nj] = 1;
						}
					}
					else if(map[ni][nj]!=3) {
						map[ni][nj] = 3;
						queue.add(new Spot(ni, nj));
					}
				}
			}
			if(!isSiru && time==limit) return time;
		}
		return -1;
	}
	
	static void print() {
		for(int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
		System.out.println("-------------------------");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);
		
		map = new int[N][M];
		Spot start = new Spot(0, 0);
		for(int i=0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if(map[i][j] == 3) manekins.add(new Spot(i, j));
				else if(map[i][j] == 4) start = new Spot(i, j); 
			}
		}
		
		for(Spot s : manekins) bfs(s, K, false);
		
		System.out.println(bfs(start, N*M, true));
		
	}

}
