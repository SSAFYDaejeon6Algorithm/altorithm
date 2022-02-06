package bj;

import java.util.Scanner;

public class p_2527 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int t = 0; t < 4; t++) {
			int x1 = sc.nextInt(), y1 = sc.nextInt();
			int x2 = sc.nextInt(), y2 = sc.nextInt();
			Point sq1[] = new Point[4];
			sq1[0] = new Point(x1, y1);
			sq1[1] = new Point(x1, y2);
			sq1[2] = new Point(x2, y1);
			sq1[3] = new Point(x2, y2);

			x1 = sc.nextInt(); y1 = sc.nextInt();
			x2 = sc.nextInt(); y2 = sc.nextInt();
			Point sq2[] = new Point[4];
			sq2[0] = new Point(x1, y1);
			sq2[1] = new Point(x1, y2);
			sq2[2] = new Point(x2, y1);
			sq2[3] = new Point(x2, y2);

			// case C
			/*  1  ㅡ  3 
			 *   |  |
			 *  0  ㅡ  2
			 */
			if (sq1[0].equals(sq2[3]) || sq1[1].equals(sq2[2]) || sq1[2].equals(sq2[1]) || sq1[3].equals(sq2[0])) {
				System.out.println("c");
				continue;
			}

			// case B, D
			Point xy1 = sq1[0], pq1 = sq1[3];
			Point xy2 = sq2[0], pq2 = sq2[3];
			char res = 'a';
			if (pq1.x <= xy2.x && pq1.x <= pq2.x)
				res = (pq1.x == xy2.x) ? 'b' : 'd';
			else if (xy1.x >= pq2.x && xy1.x >= xy2.x)
				res = (xy1.x == pq2.x) ? 'b' : 'd';
			else if (pq1.y <= xy2.y && pq1.y <= pq2.y)
				res = (pq1.y == xy2.y) ? 'b' : 'd';
			else if (xy1.y >= pq2.y && xy1.y >= xy2.y)
				res = (xy1.y == pq2.y) ? 'b' : 'd';
			
			// case D exception
			if(res == 'b') {
				if (pq1.x == xy2.x || xy1.x == pq2.x)
					res = (pq1.y < xy2.y || xy1.y > pq2.y) ? 'd' : 'b';
				else if (pq1.y == xy2.y || xy1.y == pq2.y)
					res = (xy1.x > pq2.x || pq1.x < xy2.x) ? 'd' : 'b';
			}
			
			System.out.println(res);
		}
	}

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			return this.x == ((Point) obj).x && this.y == ((Point) obj).y;
		}
	}
}
