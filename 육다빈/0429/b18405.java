package gold;

import java.io.*;
import java.util.*;

public class G18405_competitiveContagion {
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static class Node implements Comparable<Node>{
		int i, j, virus;
		Node(int i, int j, int virus){
			this.i = i;
			this.j = j;
			this.virus = virus;
		}
		@Override
		public int compareTo(Node o) {
			return this.virus - o.virus;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		List<Node> virus = new ArrayList<Node>();
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) virus.add(new Node(i, j, map[i][j])); 
			}
		}
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		for(int s=0; s<S; s++) {
			Collections.sort(virus);
			int size = virus.size();
			for(int i=0; i<size; i++) {
				Node n = virus.get(0);
				virus.remove(0);
				for(int d=0; d<4; d++) {
					int ni = n.i + di[d];
					int nj = n.j + dj[d];
					if(ni<0 || ni>=N || nj<0 || nj>=N || map[ni][nj]!=0) continue;
					map[ni][nj] = n.virus;
					virus.add(new Node(ni, nj, n.virus));
				}
			}
		}
		System.out.println(map[X-1][Y-1]);
	}

}
