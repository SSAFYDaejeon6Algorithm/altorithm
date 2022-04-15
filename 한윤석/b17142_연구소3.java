public class B17142_연구소3 {

	static int N,M; //크기, 바이러스 놓을 수
	static int m[][], res[][]; //맵 정보, 결과 맵 정보
	static Pos select[]; //선택한 바이러스 위치
	static int d [][] = {{1,0},{-1,0},{0,1},{0,-1}};
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		m = new int[N][N];
		res = new int[N][N];
		select = new Pos[M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) m[i][j] = Integer.parseInt(st.nextToken());
		}
		
		comb(0,0,0);
		
		if(ans == Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(ans);
	}
	
	static void comb(int sr, int sc, int cnt) {
		if(cnt == M) {
			initRes();
			
			bfs();
			int maxNum = getMaxNum();
			if(maxNum>=0) ans = Math.min(ans, maxNum);
			return;
		}
		
		for(int i=sr; i<N; i++) {
			for(int j=(i==sr?sc:0); j<N; j++) {
				if(m[i][j] < 2) continue;
				
				select[cnt] = new Pos(i,j);
				if(j==N-1) comb(i+1, 0, cnt+1);
				else comb(i, j+1, cnt+1);
			}
		}
	}
	
	static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		for(int i=0; i<M; i++) q.add(new Pos(select[i].r, select[i].c));
		int time = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			time++;
			
			for(int t=0; t<size; t++) {
				Pos p = q.poll();
				
				for(int i=0; i<4; i++) {
					int nr = p.r + d[i][0];
					int nc = p.c + d[i][1];
					
					//범위 유효성 검사 + 다음 칸이 벽이거나, 바이러스 놓을 위치거나, 더 빨리 도달한 적 있으면 넘김
					if(nr < 0 || nc < 0 || nr >= N || nc >= N || res[nr][nc] == -2 || res[nr][nc] == 0 || (res[nr][nc] > 0 && res[nr][nc] <= time)) continue;
					
					//다음칸이 비활성 바이러스인데 이미 모든 칸에 빈칸이 없으면 종료시킴
					if(res[nr][nc] == -3 && getMaxNum() >= 0) continue;
					
					res[nr][nc] = time;
					q.add(new Pos(nr,nc));
				}
			}
		}
	}
	
	//res배열을 벽은 -2, 빈 칸은 -1, 비활성 바이러스는 -3, 활성 바이러스는 0으로 초기화
	static void initRes() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(m[i][j] == 1) res[i][j] = -2; //벽 설치
				else if(m[i][j] == 0) res[i][j] = -1;
				else res[i][j] = -3;
			}
		}
		for(int i=0; i<M; i++) res[select[i].r][select[i].c] = 0;
	}
	
	//res배열에서 아직 빈칸이 있으면 -1리턴, 빈칸 없으면 가장 큰 소요시간 리턴
	static int getMaxNum() {
		int max = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(res[i][j] == -1) return -1;
				max = Math.max(max, res[i][j]);
			}
		}
		return max;
	}
	
	static class Pos{
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
