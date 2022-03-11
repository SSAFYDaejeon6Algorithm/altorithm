package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G2638_cheese {

	static int N, M;
	static char[][] map;
	static int[] di = {0, 1, 0, -1};
	static int[] dj = {1, 0, -1, 0};
	
	// 해당 칸(외부칸)과 연결된 모든 외부 칸을 탐색. c : 해당 회차에 탐색한 외부를 표시할 기호
	static void checkOutside(int i, int j, char c) {
		for(int d=0; d<4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			
			if(ni<0 || ni>=N || nj<0 || nj>=M || map[ni][nj]=='1' || map[ni][nj]==c) continue;
			map[ni][nj] = c;
			checkOutside(ni, nj, c);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		map = new char[N][M];
		int cheese = 0;
		for(int n=0; n<N; n++) {
			map[n] = br.readLine().replace(" ", "").toCharArray();
			for(int m=0;m<M; m++) if(map[n][m] == '1') cheese++;
		}
		
		char outside = 'A';
		checkOutside(0, 0, outside);
		
		int result = 0;
		while(cheese > 0) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == '1') {
						int cnt = 0;
						for(int d=0; d<4; d++) {
							if(map[i+di[d]][j+dj[d]] == outside) cnt++;
						}
						if(cnt >= 2) {
							map[i][j] = '-';
							cheese--;
						}
					}
				}
			}
			result++; outside++;
			checkOutside(0, 0, outside);
		}
		System.out.println(result);
	}

}
