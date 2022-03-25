package bj;

import java.io.*;
import java.util.*;

public class p_2251 {

	static Set<String> visited;
	static Set<Integer> ans;
	static int X, Y, Z;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		Z = Integer.parseInt(st.nextToken());

		visited = new HashSet<>();
		ans = new HashSet<>();

		dfs(0, 0, 0, 0, Z);

		// for sort
		TreeSet<Integer> sorted = new TreeSet<>();
		sorted.addAll(ans);
		
		// print result
		StringBuilder sb = new StringBuilder();
		for (int i : sorted)
			sb.append(i).append(" ");
		System.out.println(sb);
	}

	static void dfs(int from, int to, int A, int B, int C) {
		if (A == 0)
			ans.add(C);

		int water = 0;
		if (from == 3 && to == 1) {
			water = Math.min(C, X - A);
			C -= water;
			A += water;
		} else if (from == 3 && to == 2) {
			water = Math.min(C, Y - B);
			C -= water;
			B += water;
		} else if (from == 2 && to == 1) {
			water = Math.min(B, X - A);
			B -= water;
			A += water;
		} else if (from == 2 && to == 3) {
			water = Math.min(B, Z - C);
			B -= water;
			C += water;
		} else if (from == 1 && to == 3) {
			water = Math.min(A, Z - C);
			A -= water;
			C += water;
		} else if (from == 1 && to == 2) {
			water = Math.min(A, Y - B);
			A -= water;
			B += water;
		}

		// check visited
		StringBuilder key = new StringBuilder();
		key.append(A).append(" ").append(B).append(" ").append(C);
		if (visited.contains(key.toString()))
			return;
		else
			visited.add(key.toString());

		dfs(3, 1, A, B, C);
		dfs(3, 2, A, B, C);
		dfs(2, 1, A, B, C);
		dfs(2, 3, A, B, C);
		dfs(1, 3, A, B, C);
		dfs(1, 2, A, B, C);
	}
}
