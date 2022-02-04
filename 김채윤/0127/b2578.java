//백준 2578번. 빙고
//의문점 : 왜 scanner은 배열 두개에 나눠담으려하면 출력이 안되는거지??
//근데 굳이 배열두개에 나눠서 담을 필요가 있을까?
package b220127;

import java.util.Scanner;

public class b2578 {

	static int[][] bingomap; // 빙고판
	static int count; // 빙고 횟수

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		bingomap = new int[5][5]; // 빙고판

		count = 0; // 빙고

		// 빙고판 입력 받기
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				bingomap[i][j] = sc.nextInt();
			}
		}

		for (int a = 1; a <= 25; a++) {
			int num = sc.nextInt();

			// 부른 값이 있으면 0으로 대체
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (bingomap[i][j] == num)
						bingomap[i][j] = 0;
				}
			}
			rCheck(); // 가로 체크
			cCheck(); // 세로 체크
			leftCheck(); // 왼쪽 위에서 오른쪽 아래로 가는 대각선 체크 //00,11,22,33,44
			RightCheck(); // 왼쪽 아래에서 오른쪽 위로 가는 대각선 체크//40,31,22,13,04

			// 빙고가 3개 이상이면 a 출력
			if (count >= 3) {
				System.out.println(a);
				break;
			}
			count = 0;
		}
	}

	// 가로에 빙고가 있나 확인
	public static void rCheck() {
		for (int i = 0; i < 5; i++) {
			int zeroCount = 0;// 배열의 값이 불렸다면 ++할 변수
			for (int j = 0; j < 5; j++) {
				if (bingomap[i][j] == 0)
					zeroCount++;
			}
			if (zeroCount == 5)
				count++;
		}
	}

	// 세로에 빙고가 있나 확인
	public static void cCheck() {
		for (int i = 0; i < 5; i++) {
			int zeroCount = 0;
			for (int j = 0; j < 5; j++) {
				if (bingomap[j][i] == 0)
					zeroCount++;
			}
			if (zeroCount == 5)
				count++;
		}
	}

	// 왼쪽 위에서 오른쪽 아래로 가는 대각선 체크 //00,11,22,33,44
	public static void leftCheck() {
		int zeroCount = 0;
		for (int i = 0; i < 5; i++) {
			if (bingomap[i][i] == 0)
				zeroCount++;
		}
		if (zeroCount == 5)
			count++;
	}

	// 왼쪽 아래에서 오른쪽 위로 가는 대각선 체크//40,31,22,13,04
	public static void RightCheck() {
		int zeroCount = 0;
		for (int i = 0; i < 5; i++) {
			if (bingomap[i][4 - i] == 0)
				zeroCount++;
		}
		if (zeroCount == 5)
			count++;
	}
}