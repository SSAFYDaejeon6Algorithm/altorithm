package algorithm;

import java.util.Scanner;

public class Solution_b2251 {
	static boolean[][] visited = new boolean[201][201]; //방문체크
	static boolean[] ans = new boolean[201]; //답
	static int a, b, c; //물통 세 개

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
		dfs(0, 0, c);
		for (int i = 0; i < 201; i++) {
			if (ans[i]) {
				System.out.print(i + " ");
			}
		}
	}

	public static void dfs(int aa, int bb, int cc) {
		if (visited[aa][bb]) {
			return;
		}
		if (aa == 0) {
			ans[cc] = true;
		}
		visited[aa][bb] = true;
		// a물통에서 b물통으로 갈 때
		if (aa + bb > b) {
			dfs((aa + bb) - b, b, cc);
		} else {
			dfs(0, aa + bb, cc);
		}
		// b물통에서 a물통으로 갈 때
		if (bb + aa > a) {
			dfs(a, (bb + aa) - a, cc);
		} else {
			dfs(bb + aa, 0, cc);
		}
		// c물통에서 a물통으로 갈 때
		if (cc + aa > a) {
			dfs(a, bb, (cc + aa) - a);
		} else {
			dfs(cc + aa, bb, 0);
		}
		// c물통에서 b물통으로 갈 때
		if (cc + bb > b) {
			dfs(aa, b, (cc + bb) - b);
		} else {
			dfs(aa, cc + bb, 0);
		}
		dfs(aa, 0, bb + cc);
		dfs(0, bb, aa + cc);
	}
}
