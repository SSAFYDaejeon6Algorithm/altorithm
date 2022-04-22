package algorithm;

import java.util.*;
import java.io.*;

public class Solution_b2206_벽부수고이동하기 {

	static boolean[][][] visited; // 벽이 부서진 상태일때, 아닐때 따로 확인
	static int[][] arr;
	static int[][] d = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } }; // 상하좌우

	public static class Point {
		int x; // 위치
		int y; // 위치
		int movecount; // 이동 횟수
		int wall; // 벽 부순 여부

		Point(int x, int y, int movecount, int wall) {
			this.x = x;
			this.y = y;
			this.movecount = movecount;
			this.wall = wall;
		}
	}
////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // n은 세로를 뜻함
		int m = sc.nextInt(); // m은 가로를 뜻함
		
		arr = new int[n][m]; //N x M의 맵을 뜻함
		visited = new boolean[n][m][2];

		for (int i = 0; i < n; i++) {
			String[] temp = sc.next().split("");
			for (int j = 0; j < temp.length; j++) {
				arr[i][j] = Integer.parseInt(temp[j]);
			}
		}
		bfs(n, m);
	}
////////////////////////////////////////////////////////////////////////
	public static void bfs(int n, int m) {
		Queue<Point> q = new LinkedList();

		q.offer(new Point(0, 0, 1, 0));
		visited[0][0][0] = true;
		visited[0][0][1] = true;

		while (!q.isEmpty()) {
			Point p = q.poll();
			if (p.x == m - 1 && p.y == n - 1) {
				System.out.println(p.movecount); //출력!!
				return;
			}

			// 상하좌우를 확인하기
			for (int i = 0; i < 4; i++) {
				int pX = p.x + d[i][1];
				int pY = p.y + d[i][0];
				
				// 범위 내에 있을 때
				if (pX >= 0 && pX < m && pY >= 0 && pY < n) {
					
					// 만약 벽일 경우
					if (arr[pY][pX] == 1) {
						
						// 벽을 부수지 않은 상태일 경우, 벽을 부순 후 방문처리를 함
						if (p.wall == 0 && !visited[pY][pX][1]) {
							q.offer(new Point(pX, pY, p.movecount + 1, 1));
							visited[pY][pX][1] = true;
						}
					} else {
						if (!visited[pY][pX][p.wall]) {
							q.offer(new Point(pX, pY, p.movecount + 1, p.wall));
							visited[pY][pX][p.wall] = true;
						}
					}
				}
			}
		}
		System.out.println(-1);
	}
}