package bj;

import java.util.Scanner;

public class p_2477 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();

		Pair[] melons = new Pair[6];
		for (int i = 0; i < melons.length; i++)
			melons[i] = new Pair(sc.nextInt(), sc.nextInt());

		// check loop
		int x = 0, y = 0, inner_x = 0, inner_y = 0;
		for (int i = 0; i < melons.length; i++) {
			if (melons[i].dir == melons[(i + 2) % 6].dir && melons[(i + 1) % 6].dir == melons[(i + 3) % 6].dir) {
				inner_x = melons[(i + 1) % 6].val;
				inner_y = melons[(i + 2) % 6].val;
				x = melons[(i + 4) % 6].val;
				y = melons[(i + 5) % 6].val;
				break;
			}
		}

		System.out.println(((x * y) - (inner_x * inner_y)) * K);
	}

	static class Pair {
		int dir, val;

		Pair(int dir, int val) {
			this.dir = dir;
			this.val = val;
		}
	}
}
