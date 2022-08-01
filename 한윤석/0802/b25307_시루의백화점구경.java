public class B25307_시루의백화점구경 {
	
	static int R,C,K,sr,sc, ans; //행, 열, 마네킹범위, 시작 행, 시작 열, 정답
	static int m[][]; //0빈칸, 1벽, 2의자, 3마네킹, 4초기위치
	static boolean visit [][]; //방문 여부
	static boolean marking[][]; //마네킹 범위 체크용 방문여부
	static int d[][] = {{1,0},{-1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		m = new int[R][C];
		visit = new boolean[R][C];
		marking = new boolean[R][C];
		
		//input
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				m[i][j] = Integer.parseInt(st.nextToken());
				if(m[i][j] == 4) {
					sr = i;
					sc = j;
				}
			}
		}
		
		//모든 맵 돌아다니면서 마네킹일 때 주변 K범위 마킹
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(m[i][j] == 3 && !marking[i][j]) markRange(i,j);
			}
		}
		
		if(bfs()) System.out.println(ans);
		else System.out.println(-1);
	}
	
	static boolean bfs() {
		Queue<int []> q = new LinkedList<>();
		q.add(new int [] {sr, sc, 0});
		visit[sr][sc] = true;
		
		while(!q.isEmpty()) {
			int [] p = q.poll();
			
			if(m[p[0]][p[1]] == 2) {
				ans = p[2];
				return true;
			}
			
			for(int i=0; i<4; i++) {
				int nr = p[0] + d[i][0];
				int nc = p[1] + d[i][1];
				
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc] || m[nr][nc] == 1 || m[nr][nc] == 3) continue;
				
				visit[nr][nc] = true;
				q.add(new int [] {nr, nc, p[2]+1});
			}
		}
		
		return false;
	}
	
	static void markRange(int r, int c) {
		marking[r][c] = true;
		Queue<int []> q = new LinkedList<>();
		q.add(new int [] {r, c, 0});
		
		while(!q.isEmpty()) {
			int [] p = q.poll();
			
			if(p[2] >= K) continue;
			
			for(int i=0; i<4; i++) {
				int nr = p[0] + d[i][0];
				int nc = p[1] + d[i][1];
				
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || marking[nr][nc] || m[nr][nc] == 1) continue;
				
				marking[nr][nc] = true;
				
				//만약 다음칸이 마네킹이면 0번 움직인거로 초기화하고 넣음
				if(m[nr][nc] == 3) q.add(new int [] {nr, nc, 0});
				else {
					m[nr][nc] = 1;
					q.add(new int [] {nr, nc, p[2]+1});
				}
			}
		}
	}
}
