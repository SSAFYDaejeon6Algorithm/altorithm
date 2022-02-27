package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3184_sheep {

	static int R, C, sheep, wolf, resSheep=0, resWolf=0;
	static char[][] map;
	
	static int[] di = { -1, 0, 1, 0};
	static int[] dj = { 0, 1, 0, -1};
	
	static void dfs(int i, int j) {
		if(map[i][j]=='o') sheep++;
		else if(map[i][j]=='v') wolf++;
		map[i][j] = '#';
		
		for(int d=0; d<4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni>=0 && ni<R && nj>=0 && nj<C && map[ni][nj]!='#') 	bfs(ni, nj);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		
		map = new char[R][C];
		for(int r=0; r<R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]!='#') {
					sheep = wolf = 0;
					dfs(i, j);
					if(wolf < sheep) resSheep += sheep;
					else resWolf += wolf;
				}
			}
		}
		System.out.println(resSheep + " " + resWolf);
	}

}
