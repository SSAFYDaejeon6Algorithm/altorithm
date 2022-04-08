public class B20056_마법사상어와파이어볼 {

	static int N,M,K; //맵 크기, 초기 파이어볼 개수, 이동 수
	static int d [][] = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	static Ball m [][]; //합쳐진 파이어볼 정보 담을 배열
	static Queue<Ball> q = new LinkedList<>(); //파이어볼 정보 담은 큐
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		m = new Ball[N+1][N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int mm = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			q.add(new Ball(r,c,mm,s,d,1));
		}
		
		for(int i=0; i<K; i++) {
			condition1();
			condition2();
		}
		
		int ans = 0;
		while(!q.isEmpty()) ans += q.poll().m;
		
		System.out.println(ans);
	}
	
	static void condition1() { //속력만큼 이동한 칸으로 바꿔서 큐에 담음
		int size = q.size();
		for(int i=0; i<size; i++) {
			Ball cur = q.poll();
			int nr = moduler(cur.r + d[cur.d][0] * cur.s); 
			int nc = moduler(cur.c + d[cur.d][1] * cur.s);
			q.add(new Ball(nr, nc, cur.m, cur.s, cur.d, 1));
		}
	}
	
	static void condition2() {
		m = new Ball[N+1][N+1];
		
		//같은 칸에 있는 파이어볼을 하나로 합치고 방향을 설정
		while(!q.isEmpty()) { 
			Ball cur = q.poll();
			if(m[cur.r][cur.c] == null) m[cur.r][cur.c]= cur;
			else {
				m[cur.r][cur.c].m += cur.m;
				m[cur.r][cur.c].s += cur.s;
				m[cur.r][cur.c].cnt++;
				if(m[cur.r][cur.c].d % 2 == 0 && m[cur.r][cur.c].d >= 0) {
					if(cur.d % 2 == 0) m[cur.r][cur.c].d = 0;
					else m[cur.r][cur.c].d = -1;
				}else if(m[cur.r][cur.c].d % 2 != 0 && m[cur.r][cur.c].d >= 0) {
					if(cur.d % 2 != 0) m[cur.r][cur.c].d = 1;
					else m[cur.r][cur.c].d = -1;
				}
			}
		}
		
		//파이어볼을 4개로 나눠서 큐에 담음
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(m[i][j] != null && m[i][j].cnt == 1) q.add(m[i][j]);
				else if(m[i][j] != null && m[i][j].cnt >= 2) {
					int dm = m[i][j].m / 5;
					int ds = m[i][j].s / m[i][j].cnt;
					
					//방향이 모두 짝수이거나 홀수였던 경우 {0, 2, 4, 6}
					if(dm > 0 && m[i][j].d >= 0) { 
						q.add(new Ball(i,j,dm,ds,0,1));
						q.add(new Ball(i,j,dm,ds,2,1));
						q.add(new Ball(i,j,dm,ds,4,1));
						q.add(new Ball(i,j,dm,ds,6,1));
					}
					//그 외의 경우 {1, 3, 5, 7}
					else if(dm > 0 && m[i][j].d < 0) {
						q.add(new Ball(i,j,dm,ds,1,1));
						q.add(new Ball(i,j,dm,ds,3,1));
						q.add(new Ball(i,j,dm,ds,5,1));
						q.add(new Ball(i,j,dm,ds,7,1));
					}
				}
			}
		}
	}
	
	//맵의 범위를 넘어갈 때의 모듈러연산
	static int moduler(int n) {
		if(n % N == 0) return N;
		
		if(n > 0) return n % N;
		else return N - Math.abs(n) % N;
	}
	
	static class Ball{
		int r,c,m,s,d,cnt; //행, 열, 질량, 속력, 방향
		public Ball(int r, int c, int m, int s, int d, int cnt) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
			this.cnt = cnt;
		}
	}
}
