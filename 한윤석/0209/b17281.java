package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//백준 17281 볼
public class B17281 {
	static int[][] inputs;
	static boolean[] isSelected = new boolean[9];
	static int[] players = new int[9]; // i번 타석에 서는 선수의 번호
	static int out = 0;
	static int score = 0;
	static int tempScore = 0;
	static int lastPlayer = 0;
	static int inning;
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		inning = Integer.parseInt(br.readLine());
		inputs = new int[inning][9];
		for (int i = 0; i < inning; i++) {
			String[] rates = br.readLine().split(" ");
			for (int j = 0; j < 9; j++)
				inputs[i][j] = Integer.parseInt(rates[j]);
		}

		players[3] = 0;
		isSelected[0] = true;
		play(0);
		System.out.println(score);
	}

	public static void play(int cnt) {
		if (cnt == 9) {
			for (int i = 0; i < inning; i++) {
				int[] playerInfo = new int[9]; // i번 타석에 서는 선수의 히팅 정보. playerInfo[3]은 0으로 고정
				for (int j = 0; j < 9; j++) {
					playerInfo[j] = inputs[i][players[j]];
				}
				//
				while (out != 3) {
					for (int j = lastPlayer; j < 9; j++) {
						run(playerInfo[j]);
						if (out == 3) {
							lastPlayer = j==8 ? 0 : j + 1;
							break;
						}
						if (j == 8) j = (-1);
					}
				}
				out = 0;
				q.clear();
			}
			init();
			
			return;
		}
		if (cnt == 3) {
			play(cnt + 1);
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (isSelected[i]) continue;
			
			players[cnt] = i;
			isSelected[i] = true;
			play(cnt + 1);
			isSelected[i] = false;
		}
	}

	public static void run(int hit) {
		if (hit == 0)
			out++;
		else if (hit >= 1) {
			int size = q.size();
			for(int i=0; i<size; i++) {
				int current = q.peek() + hit;
				q.remove();
				q.add(current);
			}
			for(int i=0; i<size; i++) {
				int current = q.peek();
				if(current >= 4) {
					tempScore++;
					q.remove();
				}
				else break;
				
			}
			if (hit == 4) tempScore++;
			else q.add(hit);
		}
	}

	public static void init() {
		lastPlayer=0;
		score = Math.max(score, tempScore);
		tempScore = 0;
	}

}
