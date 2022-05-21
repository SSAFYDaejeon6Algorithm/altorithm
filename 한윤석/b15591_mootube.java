package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B15591_MooTube {

	static int N,Q;
	static LinkedList<Edge> edges[];
	static boolean visit [];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		edges = new LinkedList [N+1];
		visit = new boolean [N+1];
		
		for(int i=1; i<=N; i++) edges[i] = new LinkedList<>();
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			edges[p].add(new Edge(q, r));
			edges[q].add(new Edge(p, r));
		}
		
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			System.out.println(bfs(k, v));
		}
	}
	
	static int bfs(int k, int v) {
		int cnt = 0;
		visit = new boolean [N+1];
		Queue<Integer> q = new LinkedList<>();
		visit[v] = true;
		
		for(Edge e : edges[v]) {
			if(e.r >= k) {
				q.add(e.to);
				visit[e.to] = true;
				cnt++;
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			int size = edges[cur].size();
			
			for(int i=0; i<size; i++) {
				Edge e = edges[cur].get(i);
				
				if(visit[e.to] || e.r < k) continue;
				
				visit[e.to] = true;
				cnt++;
				q.add(e.to);
			}
		}
		
		return cnt;
	}
	
	static class Edge{
		int to, r;
		public Edge(int to, int r) {
			this.to = to;
			this.r = r;
		}
	}
}
