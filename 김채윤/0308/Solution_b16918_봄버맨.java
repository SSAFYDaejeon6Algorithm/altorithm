import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_b16918_봄버맨 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");

		int R = Integer.parseInt(inputs[0]);
		int C = Integer.parseInt(inputs[1]);
		int N = Integer.parseInt(inputs[2]);

		char[][] map = new char[R][C];
		int[][] bt = new int[R][C];

		for (int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = tmp.charAt(j);
				if (map[i][j] == 'O') {
					bt[i][j] = 3; // 폭탄이 터질 시간
				}
			}
		}
		int time = 0;
		int[] mi = { 1, -1, 0, 0 };
		int[] mj = { 0, 0, 1, -1 };

		while (time++ < N) {

			if (time % 2 == 0) {
				// 비어있는 모든 칸에 폭탄 설치
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (map[i][j] == '.') {
							map[i][j] = 'O';
							bt[i][j] = time + 3;
						}
					}
				}
				
			} else if (time % 2 == 1) {
				//폭탄터뜨리기
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (bt[i][j] == time) {
							map[i][j] = '.';
							for (int d = 0; d < 4; d++) {
								int ni = i + mi[d];
								int nj = j + mj[d];

								if (ni < 0 || nj < 0 || ni >= R || nj >= C)
									continue;

								if (map[ni][nj] == 'O' && bt[ni][nj] != time) {
									map[ni][nj] = '.';
									bt[ni][nj] = 0;
								}
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < R; i++) {
			System.out.println(map[i]);
		}
	}
}