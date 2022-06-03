package bj;

import java.util.*;
import java.io.*;

public class p_15591 {

	static ArrayList<ArrayList<Node>> graph;
	static int N, Q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for(int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());
		
		// input graph
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Node(to, r));
			graph.get(to).add(new Node(from, r));
		}
		
		StringBuilder sb = new StringBuilder();
		
		// input questions
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			sb.append(getUSADO(k, v)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int getUSADO(int k, int v) {
		int ret = 0;
		
		// BFS
		boolean visited[] = new boolean[N + 1];
		for(int i = 0; i < visited.length; i++)
			visited[i] = false;
		
		Queue<Integer> q = new LinkedList<>();
		visited[v] = true;
		q.add(v);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i = 0; i < graph.get(cur).size(); i++) {
				int next = graph.get(cur).get(i).n;
				int r = graph.get(cur).get(i).r;
				
				// not visited
				if(!visited[next] && r >= k) {
					visited[next] = true;
					q.add(next);
					ret++;
				}
			}
		}
		
		return ret;
	}
	
	static class Node {
		int n, r;
		Node(int n, int r) {
			this.n = n;	// to
			this.r = r;	// weight
		}
	}
}
