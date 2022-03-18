package bj;

import java.util.*;
import java.io.*;

public class p_2448 {

	static char pic[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		pic = new char[N][2* N - 1];

		star(N, 0, 0);

		// print result
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2 * N - 1; j++)
				sb.append(pic[i][j] > 0 ? '*' : ' ');
			sb.append('\n');
		}
		System.out.print(sb);
	}

	static void star(int n, int x, int y) {
		if (n == 3) {
			// 반복문은 거부한다
			pic[x][y + 2] = '*';
			pic[x + 1][y + 1] = pic[x + 1][y + 3] = '*';
			pic[x + 2][y] = pic[x + 2][y + 1] = pic[x + 2][y + 2] = pic[x + 2][y + 3] = pic[x + 2][y + 4] = '*';
			return;
		}
		
		star(n / 2, x, y + n/2);			// top
		star(n / 2, x + n/2, y); 			// left
		star(n / 2, x + n/2, y + n);		// right
	}
}
