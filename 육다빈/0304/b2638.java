import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int N, M;
	static char[][] map;	//O:치즈 외부 / I:치즈 내부 / 1:치즈 / C:녹을예정인 치즈
	static int[] di = {0, 1, 0, -1};
	static int[] dj = {1, 0, -1, 0};
	
	// 해당 칸이 치즈 밖이나 안의 공간인지 확인
	static boolean isInside(int i, int j, int from) {
		for(int d=0; d<4; d++) {
			if(from != -1 && d == (from+2)%4) continue;
			int ni = i + di[d];
			int nj = j + dj[d];
			
			if(ni<0 || ni>=N || nj<0 || nj>=M || map[ni][nj]=='O') return false;
			else if(map[ni][nj] == '0' && !isInside(ni, nj, d)) {
				map[ni][nj] = 'O';
				return false;
			}
		}
		map[i][j] = 'I';
		return true;
	}
	
	// 치즈 내부공간이었던 공간이, 현재도 유효한지(구멍뚫리지 않았는지) 체크
	static boolean checkInside(int i, int j, int from) {
		for(int d=0; d<4; d++) {
			if(from != -1 && d == (from+2)%4) continue;
			int ni = i+di[d];
			int nj = j+dj[d];
			if(ni<0 || ni>=N || nj<0 || nj>=M || map[ni][nj]=='C' || map[ni][nj]=='O' || (map[ni][nj]=='I' && !checkInside(ni, nj, d))) {
				map[i][j] = 'O';
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 데이터 입력받기
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		map = new char[N][M];
		int cheese = 0;
		for(int n=0; n<N; n++) {
			map[n] = br.readLine().replace(" ", "").toCharArray();
			for(int m=0;m<M; m++) if(map[n][m] == '1') cheese++;	//입력받으면서 총 치즈 개수 세기
		}
		
		// 2. 치즈 안의 빈 공간 체크
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]=='0' && !isInside(i, j, -1)) map[i][j] = 'O';
			}
		}
		
		// 3. 치즈 녹이기
		int result = 0;
		while(cheese > 0) {	// 치즈가 남아있는동안 반복
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == '1') {	// 치즈를 발견하면 사방탐색
						int cnt = 0;
						for(int d=0; d<4; d++) {
							if(map[i+di[d]][j+dj[d]]=='O') cnt++;	// 외부와 접한 면의 개수 세기
						}
						if(cnt >= 2) {	// 녹을 예정인 치즈 표시
							map[i][j] = 'C';
							cheese--;
						}
					}
				}
			}// 치즈 내부 공간이 뚫렸는지 탐색
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 'C') map[i][j] = 'O';
					else if(map[i][j] == 'I') checkInside(i, j, -1);
				}
			}
			result++;
		}
		System.out.println(result);
	}
	
	// 코드 확인용
	static void print() {
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
}
