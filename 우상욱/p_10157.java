package bj;

import java.util.*;
import java.io.*;

public class p_10157 {

	static int queue[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();
		int R = sc.nextInt();
		int K = sc.nextInt();

		// exception
		if (K > C * R) {
			System.out.println(0 + "");
			return;
		}

		queue = new int[R][C];
		int cnt = 1;
		int status = 0; //
		int x = R, y = 0;

		int dx[] = { -1, 0, 1, 0 };
		int dy[] = { 0, 1, 0, -1 };

		while (cnt <= K) {
			int nx = x + dx[status];
			int ny = y + dy[status];

			if (0 <= nx && nx < R && 0 <= ny && ny < C && queue[nx][ny] == 0) {
				x = nx;
				y = ny;
			} else {
				status = (status + 1) % 4; // change direction
				x += dx[status];
				y += dy[status];
			}

			queue[x][y] = cnt++;
		}
		
		// print result
		System.out.println((y + 1) + " " + (R - x));
	}
}
