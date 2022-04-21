public class B2206_벽부수고이동하기 {

	static int R,C; //행, 열
	static char m[][]; //맵 정보
	static int memo[][][]; //행, 열, 벽 부쉈는지 여부(0 = 안부숨)
	static int d[][] = {{1,0},{-1,0},{0,1},{0,-1}};
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<int []> q = new LinkedList<>();
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		m = new char[R][C];
		memo = new int[R][C][2];
		
		for(int i=0; i<R; i++) {
			m[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				memo[i][j][0] = Integer.MAX_VALUE;
				memo[i][j][1] = Integer.MAX_VALUE;
			}
		}
		
		memo[0][0][0] = 1;
		memo[0][0][1] = 1;
		bfs();

		if(ans == Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(ans);
	}
	
	static void bfs() {
		Queue<int []> q = new LinkedList<>();
		q.add(new int[] {0,0,0}); //행, 열, 부수기 사용 여부
		
		while(!q.isEmpty()) {
			int [] p = q.poll();

			//목적지 도달했으면
			if(p[0] == R-1 && p[1] == C-1) {
				ans = Math.min(memo[p[0]][p[1]][p[2]], ans);
				continue;
			}
			
			for(int i=0; i<4; i++) {
				int nr = p[0] + d[i][0];
				int nc = p[1] + d[i][1];
				
				if(!isValid(p, nr, nc)) continue;
				
				if(m[nr][nc] == '0') { //갈 수 있는 칸이면 자기 자신의 부순 여부 상태 그대로 갱신
					q.add(new int[] {nr, nc, p[2]});
					memo[nr][nc][p[2]] = Math.min(memo[nr][nc][p[2]], memo[p[0]][p[1]][p[2]]+1);
				}
				//부순 적 없는데 벽 만났으면, 부쉈다고 체크하고 부순 상태의 memo로 갱신
				else if(p[2] == 0 && m[nr][nc] == '1') { 
					q.add(new int[] {nr, nc, 1});
					memo[nr][nc][1] = Math.min(memo[nr][nc][1], memo[p[0]][p[1]][0]+1);
				}
				
			}
		}
	}
	
	static boolean isValid(int [] p, int nr, int nc) {
		if(nr < 0 || nc < 0 || nr >= R || nc >= C) return false; //범위 검사
		if(memo[nr][nc][p[2]] <= memo[p[0]][p[1]][p[2]]+1) return false; //최소 걸음 검사
		if(p[2] == 1 && m[nr][nc] == '1') return false; //이미 벽 부순 적 있는데 또 벽일 때 검사
		
		return true;
	}
}
