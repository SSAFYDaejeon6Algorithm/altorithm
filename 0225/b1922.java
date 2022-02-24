package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G1922_networkConnection {

	static int[] parents;
	
	static class Edge implements Comparable<Edge>{
		public int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static int findSet(int a) {
		if(parents[a] == a) return a;
		else return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		if(rootA==rootB) return false;
		else{
			parents[rootB] = rootA;
			return true;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		Edge[] list = new Edge[M]; 
		for(int m=0; m<M; m++) {
			String[] line = br.readLine().split(" ");
			int u = Integer.parseInt(line[0]);
			int v = Integer.parseInt(line[1]);
			int w = Integer.parseInt(line[2]);
			list[m] = new Edge(u, v, w);
		}
		if(N==1) {
			int min = Integer.MAX_VALUE;
			for(Edge e : list) {
				min = Math.min(min, e.weight);
			}
			System.out.println(min);
			return;
		}
		
		Arrays.sort(list);
		
		parents = new int[N+1];
		for(int n=1; n<=N; n++) parents[n] = n;
		
		int sum = 0, cnt = 0;
		for(Edge e : list) {
			if(union(e.to, e.from)){
				sum += e.weight;
				if(++cnt==N-1) break;
			}
		}
		System.out.println(sum);
	}

}
