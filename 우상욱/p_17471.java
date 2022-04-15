package bj;

import java.io.*;
import java.util.*;

public class p_17471 {

	static ArrayList<ArrayList<Integer>> graph;
	static int[] popularity;
	static int visited;

	static int min_diff, N;

	public static void main(String[] args) throws IllegalArgumentException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// popularity input
		popularity = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			popularity[i] = Integer.parseInt(st.nextToken());

		// graph input;
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());

			// add nodes
			st = new StringTokenizer(br.readLine());
			int num_nodes = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num_nodes; j++)
				graph.get(i).add(Integer.parseInt(st.nextToken()) - 1);
		}

		min_diff = Integer.MAX_VALUE;
		for (int p = 1; p < Math.pow(2, N) - 1; p++) {
			// check connectivity
			visited = p;
			if (!isConnect())
				continue;

			visited = p;
			visited ^= (int) Math.pow(2, N) - 1;
			if (!isConnect())
				continue;

			// calculate popularity
			int pop = 0, rest_pop = 0;
			for (int i = 0; i < N; i++) {
				if ((p & 1 << i) == 1 << i)
					pop += popularity[i];
				else
					rest_pop += popularity[i];
			}

			min_diff = Math.min(min_diff, Math.abs(pop - rest_pop));
		}

		System.out.println(min_diff == Integer.MAX_VALUE ? -1 : min_diff);
	}

	static boolean isConnect() {
		for (int i = 0; i < N; i++) {
			if ((visited & 1 << i) != 1 << i) {
				dfs(i);
				break;
			}
		}

		if (visited != Math.pow(2, N) - 1)
			return false;
		else
			return true;
	}

	static void dfs(int n) {
		visited |= 1 << n;

		for (int i = 0; i < graph.get(n).size(); i++) {
			int nextNode = graph.get(n).get(i);

			if ((visited & 1 << nextNode) != (1 << nextNode))
				dfs(nextNode);
		}
	}
}
