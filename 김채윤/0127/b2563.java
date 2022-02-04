//백준 2563. 색종이

package b220127;

import java.util.Scanner;

public class b2563 {
	public static void main(String[] args) {

		int[][] whitepaper = new int[100][100];

		Scanner sc = new Scanner(System.in);

		int count = 0; // 하얀 종이 위의 1을 셀 변수
		int N = sc.nextInt(); // 색종이 개수

		for (int i = 0; i < N; i++) {
			int black_x = sc.nextInt(); // blackpaper x값 입력받기
			int black_y = sc.nextInt(); // blackpaper y값 입력받기

			for (int a = black_x; a < black_x + 10; a++) { // x+10
				for (int b = black_y; b < black_y + 10; b++) // y+10
					whitepaper[a][b] = 1; // blackpaper가 올라간 부분은 1로 저장

			}
		}

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (whitepaper[i][j] == 1)
					count++;

			}
		}
		System.out.println(count);
	}

}
