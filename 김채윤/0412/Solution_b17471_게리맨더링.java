package algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_b17471_게리맨더링 {
	static int N;
	static int[] people; // 구역 인구 정보
	static boolean[][] adj; // 구역간 인접행렬

	static boolean[] select; // 선거구 t or f
	static int ans; // 두 선거구 인구총합 차이

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		people = new int[N + 1];
		select = new boolean[N + 1];
		adj = new boolean[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			people[i] = sc.nextInt();
		}

		for (int i = 1; i <= N; i++) {
			int tmp = sc.nextInt(); 
			for (int j = 0; j < tmp; j++) {
				adj[i][sc.nextInt()] = true;
			}
		}

		ans = Integer.MAX_VALUE;
		powerset_bit();
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	private static void powerset_bit() {
		for (int i = 0; i < (1 << N); i++) { 
			select = new boolean[N + 1]; 
			for(int j=0; j<N; j++) {
				if((i & (1<<j)) > 0)
					select[j+1] = true; 
			} 
			
			int startA = 0, startB = 0; 
			int cntA = 0, cntB = 0;
			for (int t = 1; t <= N; t++) {
				if (select[t]) {
					startA = t;
					cntA++;
				} else {
					startB = t;
					cntB++;
				}
			}

			int peopleA = bfs(startA, cntA); 
			int peopleB = bfs(startB, cntB);

			if (peopleA > 0 && peopleB > 0)
				ans = Math.min(ans, Math.abs(peopleA - peopleB));
		}
	}

	private static void powerset(int idx) {
		if (idx == N + 1) { 
			int startA = 0, startB = 0; 
			int cntA = 0, cntB = 0;
			for (int i = 1; i <= N; i++) {
				if (select[i]) {
					startA = i;
					cntA++;
				} else {
					startB = i;
					cntB++;
				}
			}

			int peopleA = bfs(startA, cntA);
			int peopleB = bfs(startB, cntB);

			if (peopleA > 0 && peopleB > 0)
				ans = Math.min(ans, Math.abs(peopleA - peopleB));

			return;
		}
		select[idx] = true;
		powerset(idx + 1);
		select[idx] = false;
		powerset(idx + 1);
	}

	private static int bfs(int start, int cnt) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visit = new boolean[N + 1];

		queue.add(start);
		visit[start] = true;

		int peopleCnt = 0;
		int linkCnt = 0;
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			linkCnt++; 
			peopleCnt = peopleCnt + people[now]; 

			for (int i = 1; i <= N; i++) {
				if (adj[now][i] && select[now] == select[i] && !visit[i]) {
					queue.add(i);
					visit[i] = true;
					
				}
			}
		}
		return linkCnt == cnt ? peopleCnt : -1;
	}
}
