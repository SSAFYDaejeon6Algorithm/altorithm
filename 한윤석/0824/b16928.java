public class B16928_뱀과사다리게임 {
	
	static int to [] = new int [101]; // [i]에 도달하면 to[i]로 이동한다
	static boolean visit [] = new boolean [101]; //방문여부

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N+M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			to[x] = y;
		}
		visit[1] = true;
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<int []> q = new LinkedList<>();
		q.add(new int [] {1, 0}); // 숫자, 이동 횟수
		while(!q.isEmpty()) {
			int qq [] = q.poll();
			
			if(qq[0] == 100) return qq[1];
			
			for(int i=1; i<=6; i++) {
				int next = qq[0] + i;
				if(next > 100) break;
				
				if(visit[next]) continue;
				
				visit[next] = true;
				if(to[next] != 0) {
					visit[to[next]] = true;
					q.add(new int[] {to[next], qq[1]+1});
				}
				else q.add(new int [] {next, qq[1]+1});
			}
		}
		
		return 0;
	}
}
