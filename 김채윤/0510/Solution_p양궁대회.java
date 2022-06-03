package algorithm;

import java.util.*;
import java.io.*;

public class Solution_p양궁대회 {
	static int max;
	static int[] ans;

	public static void main(String[] args) {
		int n = 5;
		int[] info = { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 };

		System.out.println(Arrays.toString(solution(n, info)));
	}
	
	public static int[] solution(int n, int[] info) {
		ans = new int[11];

		dfs(0, n, 0, new int[n], info);
		// 라이언이 이기지 못한다면... -1을 리턴...
		return max == 0 ? new int[] { -1 } : ans;
	}

	public static void dfs(int start, int r, int num, int[] result, int[] info) {
		if (num == r) {
			
			int ryan = 0;
			int apeach = 0;
			
			int[] info2 = new int[11];
			for (int score : result) {
				info2[10 - score]++;
			}

			for (int i = 0; i < 11; i++) {	
				
				int score = 10 - i;
				// if 라이언 win
				if (info2[i] > info[i]) {
					ryan += score;
				}
				// if 어피치 win
				else if (info2[i] < info[i]) {
					apeach += score;
				}
				// 어피치 & 라이언이 같은 상황일때
				else if (info[i] != 0 && info2[i] == info[i]) {
					apeach += score;
				}
			}

			if (ryan - apeach > max) {
				max = ryan - apeach;
				ans = info2;
			}
			return;
		}

		for (int i = start; i < 11; i++) {
			// 라이언이 쏜 과녁 점수 저장
			result[num] = i;
			dfs(i, r, num + 1, result, info);
		}
	}
}
