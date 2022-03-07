package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class S16918_bomberman {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		int R = Integer.parseInt(line[0]);
		int C = Integer.parseInt(line[1]);
		int N = Integer.parseInt(line[2]);
		
		char[][] map = new char[R][C];
		for(int r=0; r<R; r++)	map[r] = br.readLine().toCharArray();
		
		int[] di = {-1, 0, 1, 0};
		int[] dj = {0, 1, 0, -1};
		Stack<Integer> bombList = new Stack<>();
		
		for(int n=0; n<N; n++) {
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					switch(map[i][j]) {
					case '.': map[i][j] = '-';  break;	// 초기 시작 시 1초 쉬기
					case '-': map[i][j] = 'O';  break;	// 비어있는 땅에 폭탄 설치
					case 'O': map[i][j] = '1';  break;	// 폭탄 설치 후 1초 경과
					case '1': map[i][j] = '2';  break;	// 폭탄 설치 후 2초 경과
					case '2': 	// 폭탄 설치 후 3초 경과 - 주변의 터질 땅들 인덱스 저장
						map[i][j] = '-';
						for(int d=0; d<4; d++) {
							int ni = i + di[d];
							int nj = j + dj[d];
							if(ni>=0 && ni<R && nj>=0 && nj<C) {
								bombList.add(nj);
								bombList.add(ni);
							}
						}
						break;
					}
				}
			}
			while(!bombList.isEmpty()) map[bombList.pop()][bombList.pop()] = '-';	// 폭탄 한번에 터트리기
		}
		for(int i=0; i<R; i++) {	// 결과 출력
			for(int j=0; j<C; j++) map[i][j] = (map[i][j]=='-' || map[i][j]=='.') ? '.' : 'O';
			System.out.println(new String(map[i]));
		}
	}

}
