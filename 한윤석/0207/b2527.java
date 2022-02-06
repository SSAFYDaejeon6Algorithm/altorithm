package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2527 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {
			String[] numbers = br.readLine().split(" ");
			int sqr1[][] = new int[2][2];
			int sqr2[][] = new int[2][2];
			sqr1[0][0] = Integer.parseInt(numbers[0]);
			sqr1[0][1] = Integer.parseInt(numbers[1]);
			sqr1[1][0] = Integer.parseInt(numbers[2]);
			sqr1[1][1] = Integer.parseInt(numbers[3]);
			sqr2[0][0] = Integer.parseInt(numbers[4]);
			sqr2[0][1] = Integer.parseInt(numbers[5]);
			sqr2[1][0] = Integer.parseInt(numbers[6]);
			sqr2[1][1] = Integer.parseInt(numbers[7]);

			printResult(sqr1, sqr2);
		}
	}

	static void printResult(int[][] sqr1, int[][] sqr2) {
		char result = ' ';

		// 한 사각형의 점이 공유될 때
		if ((sqr1[1][0] == sqr2[0][0] && sqr1[1][1] == sqr2[0][1])
				|| (sqr1[0][0] == sqr2[1][0] && sqr1[1][1] == sqr2[0][1])
				|| (sqr1[1][0] == sqr2[0][0] && sqr1[0][1] == sqr2[1][1])
				|| (sqr1[0][0] == sqr2[1][0] && sqr1[0][1] == sqr2[1][1])) {
			result = 'c';
		}
		// 한 사각형의 면과 다른 사각형의 면이 공유될 때
		else if ((sqr1[1][0] == sqr2[0][0] && sqr1[1][1] != sqr2[0][1])
				|| (sqr1[0][0] == sqr2[1][0] && sqr1[1][1] != sqr2[0][1])
				|| (sqr1[1][0] != sqr2[0][0] && sqr1[0][1] == sqr2[1][1])
				|| (sqr1[0][0] != sqr2[1][0] && sqr1[0][1] == sqr2[1][1])) {
			result = 'b';
		}
		// 두 사각형이 서로 만나지 않을 때
		else if (sqr1[1][0] < sqr2[0][0] || sqr2[1][0] < sqr1[0][0] || sqr1[1][1] < sqr2[0][1]
				|| sqr1[0][1] > sqr2[1][1])
			result = 'd';
		else
			result = 'a';

		System.out.println(result);
	}
}
