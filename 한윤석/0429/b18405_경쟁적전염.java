public class B18405_경쟁적전염 {

	static int N,K,S,R,C; //맵 크기, x, 시간, 정답행, 정답열
	static int m[][]; //맵 정보
	static int d[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static PriorityQueue<Virus> virus = new PriorityQueue<>(); //바이러스 정보
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		m = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				m[i][j] = Integer.parseInt(st.nextToken());
				if(m[i][j] > 0) virus.add(new Virus(i,j,m[i][j]));
			}
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		for(int s=0; s<S; s++) play();
		
		System.out.println(m[R][C]);
	}
	
	static void play() {
		int size = virus.size();
		Queue<Virus> q = new LinkedList<>(); //새로 추가되는 바이러스 담을 큐
		
		for(int i=0; i<size; i++) {
			Virus v = virus.poll(); //어차피 한 번 선택한 바이러스 칸은 다시 안봐도 되므로 poll 
			for(int j=0; j<4; j++) {
				int nr = v.r + d[j][0];
				int nc = v.c + d[j][1];
				
				if(nr <= 0 || nc <= 0 || nr > N || nc > N || m[nr][nc] > 0) continue;
				m[nr][nc] = m[v.r][v.c];
				q.add(new Virus(nr, nc, v.n));
			}
		}
		
		while(!q.isEmpty()) virus.add(q.poll());
	}
	
	static class Virus implements Comparable<Virus>{
		int r, c, n;
		public Virus(int r, int c, int n) {
			this.r = r;
			this.c = c;
			this.n = n;
		}
		@Override
		public int compareTo(Virus o) {
			return this.n - o.n;
		}
	}
}
