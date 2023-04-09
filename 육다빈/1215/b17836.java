package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G17836_saveThePrincess {
	static int N, M, T;
	static int[][] map;
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static class Point{
		int i, j;
		boolean isTaken;
		Point(int i, int j, boolean isTaken){
			this.i = i;
			this.j = j;
			this.isTaken = isTaken;
		}
	}
	
	static String bfs() {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(0, 0, false));
		map[0][0] = -1;
		for(int t=1; t<=T; t++) {
			int size = queue.size();
			for(int s=0; s<size; s++) {
				Point now = queue.poll();
				for(int d=0; d<4; d++) {
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					
					if(ni==N-1 && nj==M-1) return t+""; // 공주님 찾기 완료
					else if(ni<0 || ni>=N || nj<0 || nj>=M || map[ni][nj]==-2
							|| ((!now.isTaken)&&((map[ni][nj]==1)||(map[ni][nj]==-1)))
							) continue;
					else if(map[ni][nj]==2 || now.isTaken) {
						queue.add(new Point(ni, nj, true));
						map[ni][nj] = -2;
					}
					else {
						queue.add(new Point(ni, nj, false));
						map[ni][nj] = -1;
					}
				}
			}
		}
		return "Fail";
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(bfs());
	}

}
