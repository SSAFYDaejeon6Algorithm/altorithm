package bj;

import java.io.*;
import java.util.*;

public class p_1967 {

	static ArrayList<Node> graph[];
	
	static boolean visited[] = new boolean[10001];
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// graph init
		graph = new ArrayList[N + 1];
		for(int i = 0; i <= N; i++)
			graph[i] = new ArrayList<Node>();
		
		// input graph node
		String inp;
		while((inp = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(inp);
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to, weight));
			graph[to].add(new Node(from, weight));
		}
		
		for(int i = 1; i <= N; i++) {
			// pass not leaf node
			if(graph[i].size() != 1)
				continue;
			
			Arrays.fill(visited, false);
			dfs(i, 0);
		}
		
		System.out.println(ans);
	}
	
	static void dfs(int node, int w_sum) {
		visited[node] = true;
		// if leaf node
		if(graph[node].size() == 1)
			ans = Math.max(ans, w_sum);
		
		for(int i = 0; i < graph[node].size(); i++) {
			int next = graph[node].get(i).to;
			int w_next = graph[node].get(i).weight;
			
			if(!visited[next]) {
				dfs(next, w_sum + w_next);
			}
		}
	}
	
	static class Node {
		int to, weight;
		Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
}
