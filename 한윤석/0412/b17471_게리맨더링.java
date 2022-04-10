public class B17471_게리맨더링 {

	static int N;
	static int pop[]; //i구역의 인구수
	static boolean m[][]; //연결관계
	static boolean select[]; //뽑은 선거구
	static boolean visit[]; //연결여부 탐색할 때 사용했는지 여부 확인용
	static List<Integer> red = new LinkedList<>(); //파란선거구
	static List<Integer> blue = new LinkedList<>(); //빨간선거구
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pop = new int[N+1];
		select = new boolean[N+1];
		m = new boolean[N+1][N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++)pop[i] = Integer.parseInt(st.nextToken());
		
		//입력
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int C = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<C; j++) {
				int adj = Integer.parseInt(st.nextToken());
				m[i][adj] = true;
				m[adj][i] = true;
			}
		}
		
		partial(0);
		if(ans == Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(ans);
	}
	
	static void partial(int cnt) {
		if(cnt == N) { //선거구를 다 뽑았으면
			visit = new boolean[N+1];
			red = new LinkedList<>();
			blue = new LinkedList<>();

			//선택된 수는 레드로, 그 외에는 블루로 넣음
			for(int i=1; i<=N; i++) {
				if(select[i]) red.add(i);
				else blue.add(i);
			}
			int rsize = red.size();
			int bsize = blue.size();
			
			if(rsize == 0 || bsize == 0) return;
			
			//지역 선거구 연결여부 확인
			dfs(red.get(0), 'r');
			for(int i=0; i<rsize; i++) if(!visit[red.get(i)]) return;
			
			dfs(blue.get(0), 'b');
			for(int i=0; i<bsize; i++) if(!visit[blue.get(i)]) return;
			
			//인구수 총합 구함
			int rsum = 0;
			int bsum = 0;
			for(int i=0; i<rsize; i++) rsum += pop[red.get(i)];
			for(int i=0; i<bsize; i++) bsum += pop[blue.get(i)];
			
			//각각의 인구 합 구하고 비교
			ans = Math.min(ans,  Math.abs(rsum-bsum));
			return;
		}
		
		select[cnt]=true;
		partial(cnt+1);
		select[cnt]=false;
		partial(cnt+1);
	}
	
	//type에 해당하는 선거구에 포함되는지 확인
	static void dfs(int idx, char type) {
		visit[idx] = true;
		
		for(int i=1; i<=N; i++) {
			if(type == 'r' && m[idx][i] && red.contains(i) && !visit[i]) dfs(i, type);
			if(type == 'b' && m[idx][i] && blue.contains(i) && !visit[i]) dfs(i, type);
		}
	}
}
