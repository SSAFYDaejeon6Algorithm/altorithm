//백준 21610 마법사 상어와 비바라기 골드5
public class B21610 {
	
	static int N,M; // N x N 칸, M개의 이동정보
	static int m[][]; //맵 정보
	static int di,si; //이동방향, 거리
	static int d[][] = {{0,0},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
	static int diagonal[][] = {{-1,-1},{-1, 1},{1, -1},{1, 1}}; //대각선 델타
	static boolean check[][]; //2번에서 사용되었는지 여부
	static Queue<Pos> cloud = new LinkedList<>(); //구름 좌표 큐

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int ans = 0;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		m = new int[N][N];
		cloud.add(new Pos(N-1,0)); //구름의 시작위치
		cloud.add(new Pos(N-2,0));
		cloud.add(new Pos(N-1,1));
		cloud.add(new Pos(N-2,1));
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) m[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			di = Integer.parseInt(st.nextToken());
			si = Integer.parseInt(st.nextToken());
			moveCloud();
			rain();
			copyWater();
			makeCloud();
		}
		
		for(int i=0; i<N; i++) for(int j=0; j<N; j++) ans+=m[i][j];
		
		System.out.println(ans);
	}
	
	//구름을 상하좌우로 연결되었다고 가정하고 di 방향으로 si만큼 이동시킴
	static void moveCloud() {
		int size = cloud.size();
		check = new boolean[N][N];
		for(int i=0; i<size; i++) {
			Pos cur = cloud.poll();
			int nr = cur.r + d[di][0]*si;
			int nc = cur.c + d[di][1]*si;
			nr = nr < 0 ? Math.abs(N-Math.abs(nr)%N)%N : (cur.r + d[di][0]*si) % N;
			nc = nc < 0 ? Math.abs(N-Math.abs(nc)%N)%N : (cur.c + d[di][1]*si) % N;
			cloud.add(new Pos(nr,nc));
		}
		
		
	}
	
	//구름의 현재 위치에 있는 물+1
	static void rain() {
		int size = cloud.size();
		for(int i=0; i<size; i++) {
			Pos cur = cloud.poll();
			m[cur.r][cur.c]++;
			check[cur.r][cur.c]= true;
			cloud.add(cur);
		}
	}
	
	//바구니의 대각선에 물이 있는 수만큼 바구니의 물 증가
	static void copyWater() {
		while(!cloud.isEmpty()) {
			Pos cur = cloud.poll();
			int cnt = 0;
			for(int i=0; i<4; i++) {
				int nr = cur.r + diagonal[i][0];
				int nc = cur.c + diagonal[i][1];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				
				if(m[nr][nc] > 0) cnt++;
			}
			m[cur.r][cur.c] += cnt; 
		}
	}
	
	//물이 2 이상 있고 사용되지 않은 자리를 구름으로 만듬
	static void makeCloud() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(m[i][j] >= 2 && !check[i][j]) {
					cloud.add(new Pos(i,j));
					m[i][j] -= 2;
				}
			}
		}
	}
	
	static class Pos{
		int r,c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
