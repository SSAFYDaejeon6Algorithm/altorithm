package bj;

import java.io.*;
import java.util.*;

public class p_20055 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// belts durability inputs
		List<Belt> upper = new LinkedList<>();
		List<Belt> lower = new LinkedList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			upper.add(upper.size(), new Belt(Integer.parseInt(st.nextToken())));
		for (int i = 0; i < N; i++)
			lower.add(0, new Belt(Integer.parseInt(st.nextToken())));

		// check zero
		int zero_duraEA = 0;
		zero_duraEA = getZeros(upper) + getZeros(lower);

		int turn = 0;
		while (zero_duraEA < K) {
			turn++;

			// step. 1
			upper.add(0, lower.get(0));
			lower.add(lower.size(), upper.get(upper.size() - 1));
			upper.remove(upper.size() - 1);
			lower.remove(0);

			// get off robot
			upper.get(upper.size() - 1).robotNum = 0;

			// step. 2
			for (int i = upper.size() - 2; i >= 0; i--) {
				Belt cur_b = upper.get(i), next_b = upper.get(i + 1);
				if(cur_b.robotNum != 0 && next_b.durability != 0 && next_b.robotNum == 0) {
					next_b.robotNum = cur_b.robotNum;
					next_b.durability--;
					 
					cur_b.robotNum = 0;
				}
				
				// get off robot
				upper.get(upper.size() - 1).robotNum = 0;
			}

			// step. 3
			if (upper.get(0).durability != 0) {
				upper.get(0).robotNum = turn;
				upper.get(0).durability--;
			}

			// step. 4
			zero_duraEA = getZeros(upper) + getZeros(lower);
		}

		System.out.println(turn);
	}

	static int getZeros(List<Belt> belts) {
		int ret = 0;
		for (Belt b : belts)
			if (b.durability == 0)
				ret++;

		return ret;
	}

	static class Belt {
		int durability;
		int robotNum;

		Belt(int d) {
			this.durability = d;
			robotNum = 0;
		}
	}
}
