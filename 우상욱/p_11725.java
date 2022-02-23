package bj;

import java.util.*;
import java.io.*;

public class p_11725 {

	static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
	static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// tree initialization
		tree.add(new ArrayList<>());
		for (int i = 0; i < N; i++)
			tree.add(new ArrayList<>());

		// node inputs
		StringTokenizer st;
		for (int i = 1; i <= N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			tree.get(to).add(from);
			tree.get(from).add(to);
		}

		// travel tree
		int ans[] = new int[N + 1];
		visited = new boolean[N + 1];

		dfs(1, 0, ans);
		
		// print result
		StringBuilder sb = new StringBuilder();
		for(int i = 2; i <= N; i++)
			sb.append(ans[i]).append("\n");
		System.out.println(sb);
	}

	static void dfs(int node, int prev_node, int parents[]) {
		visited[node] = true;
		parents[node] = prev_node; // find parent

		for (int i = 0; i < tree.get(node).size(); i++) {
			int next_node = tree.get(node).get(i);

			if (!visited[next_node])
				dfs(next_node, node, parents);
		}
	}
}
