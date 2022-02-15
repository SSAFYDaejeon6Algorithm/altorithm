package bj;

import java.util.*;
import java.io.*;

public class p_21608 {

	static int[][] students, students_favors, seats;
	static int N;

	static int dx[] = { -1, 0, 0, 1 };
	static int dy[] = { 0, -1, 1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		students = new int[N * N][3];	// 0 : student number, 1 : x, 2 : y
		students_favors = new int[N * N + 1][4];
		seats = new int[N][N];

		// inputs
		for (int i = 0; i < N * N; i++) {
			students[i][0] = sc.nextInt();
			
			// favors input
			for (int j = 0; j < 4; j++)
				students_favors[students[i][0]][j] = sc.nextInt();
		}

		// first student
		seats[1][1] = students[0][0];
		students[0][1] = 1;
		students[0][2] = 1;

		for (int n = 1; n < students.length; n++) {
			// search all favor
			List<Axis> favors = new ArrayList<>();
			for (int i = 0; i < 4; i++) {
				for (int j = n - 1; j >= 0; j--) {
					if (students_favors[students[n][0]][i] != students[j][0])
						continue;

					int fx = students[j][1]; // favor's x
					int fy = students[j][2];

					check_near_favors(fx, fy, favors);
				}
			}

			int x_toAdd, y_toAdd; // students N's axis

			// if it has no favorite friends
			if (favors.isEmpty()) {
				List<Axis> empties = new ArrayList<>();

				// search all empty seats
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (seats[i][j] != 0) // reserved seat
							continue;

						empties.add(new Axis(i, j, 0, count_near_elements(i, j, 0)));
					}
				}

				// find first one
				Collections.sort(empties);
				x_toAdd = empties.get(0).x;
				y_toAdd = empties.get(0).y;
			} else {
				// has favor
				Collections.sort(favors);
				x_toAdd = favors.get(0).x;
				y_toAdd = favors.get(0).y;
			}

			seats[x_toAdd][y_toAdd] = students[n][0];
			students[n][1] = x_toAdd;
			students[n][2] = y_toAdd;
		}

		// calculate satisfaction score
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int stu_num = seats[i][j];
				int cnt = 0;
				
				// check each favorite
				for(int k = 0; k < 4; k++)
					cnt += count_near_elements(i, j, students_favors[stu_num][k]);
				res += Math.pow(10, cnt - 1);
			}
		}
		System.out.println(res);
	}

	static int count_near_elements(int x, int y, int toCount) {
		// count empty
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (0 <= nx && nx < N && 0 <= ny && ny < N && seats[nx][ny] == toCount)
				cnt++;
		}
		return cnt;
	}

	static void check_near_favors(int x, int y, List<Axis> favors) {
		// 4 adjacent
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (0 <= nx && nx < N && 0 <= ny && ny < N && seats[nx][ny] == 0) {
				// search for duplicated one
				boolean hasDup = false;
				for (int k = 0; k < favors.size(); k++) {
					if (nx == favors.get(k).x && ny == favors.get(k).y) {
						favors.get(k).favor_cnt++;
						hasDup = true;
					}
				}

				// add to favors
				if (!hasDup) {
					favors.add(new Axis(nx, ny, 1, count_near_elements(nx, ny, 0)));
				}
			} // end if
		}
	}

	static class Axis implements Comparable<Axis> {
		int x, y, favor_cnt, empty_cnt;

		Axis(int x, int y, int favor_cnt, int empty_cnt) {
			this.x = x;
			this.y = y;
			this.favor_cnt = favor_cnt; // favorite count or empty one count
			this.empty_cnt = empty_cnt;
		}

		@Override
		public int compareTo(Axis o) {
			// compare favor -> empty -> x -> y
			if (favor_cnt == o.favor_cnt) {
				if (empty_cnt == o.empty_cnt) {
					if (x == o.x)
						return y - o.y;
					else
						return x - o.x;
				} else
					return o.empty_cnt - empty_cnt;
			} else
				return o.favor_cnt - favor_cnt;
		}
	}
}
