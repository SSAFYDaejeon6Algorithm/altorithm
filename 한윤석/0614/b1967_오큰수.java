public class B1967_트리의지름 {

	static List<Node> edges [];
	static boolean visit[];
	static int ans = 0;
	static int lnode = 1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		edges = new LinkedList[N+1];
		visit = new boolean[N+1];
		for(int i=1; i<=N; i++) edges[i] = new LinkedList<>();
		
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[from].add(new Node(to,w));
			edges[to].add(new Node(from, w));
		}
		
		dfs(1, 0);
		visit = new boolean[N+1];
		ans = 0;
		dfs(lnode, 0);
		
		System.out.println(ans);
	}
	
	static void dfs(int start, int sum) {
		visit[start] = true;
		int ssize = edges[start].size();
		
		for(int i=0; i<ssize; i++) {
			Node cur = edges[start].get(i);
			
			if(visit[cur.to]) continue;
			
			dfs(cur.to, sum + cur.w);
		}
		
		if(ans < sum) {
			ans = sum;
			lnode = start;
		}
	}
	
	static class Node{
		int to, w;
		public Node(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}
}
