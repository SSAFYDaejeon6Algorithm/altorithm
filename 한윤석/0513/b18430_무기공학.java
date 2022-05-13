public class B18430_무기공학 {

	static int m[][]; //나무 정보
	static boolean visit[][]; //사용 여부
	static int d[][] = {{0,-1,1,0}, {0,-1,-1,0}, {0,1,-1,0}, {0,1,1,0}}; //부메랑의 날개 델타 
	static int R,C;
	static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		m = new int[R][C];
		visit = new boolean [R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) m[i][j] = Integer.parseInt(st.nextToken());
		}
		
		backtrack(0,0,"");
		
		System.out.println(ans);
	}

	//centers = 현재까지 선택한 부메랑의 중심좌표를 문자열로 만듬
	static void backtrack(int r, int c, String centers) {
		if(r == R && c == 0) { //모든 나무 다 돌아봤을 때
			int sum = 0;
			
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(visit[i][j]) {
						String cur = "("+i+j+")"; //현재 좌표가 부메랑의 중심에 사용된 좌표라면
						if(centers.contains(cur)) sum += m[i][j]*2;
						else sum += m[i][j];
					}
				}
			}
			
			ans = Math.max(ans,  sum);
			return;
		}
		
		//이미 방문한 좌표면 다음 이동 좌표로 던짐
		if(visit[r][c]) {
			if(c == C-1) backtrack(r+1,0,centers);
			else backtrack(r, c+1,centers);
		}
		
		boolean flag = true; //더 이상 선택할 수 있는 부메랑이 없는지
		for(int i=r; i<R; i++) {
			for(int j=(i==r?c:0); j<C; j++) {
				if(visit[i][j]) continue;
				
				for(int k=0; k<4; k++) {
					if(!isValid(i, j, k)) continue;
					
					flag=false;
					toggleVisit(i,j,k, true); //부메랑의 위치에 해당하는 visit을 모두 true로
					
					if(j == C-1) backtrack(i+1,0, centers+"("+i+j+")");
					else backtrack(i, j+1, centers+"("+i+j+")");
					
					toggleVisit(i,j,k,false); //부메랑의 위치에 해당하는 visit을 모두 false로
				}
			}
		}
		
		if(flag) { //이번 좌표에서 부메랑을 하나도 만들지 못하면
			if(c == C-1) backtrack(r+1,0,centers);
			else backtrack(r, c+1,centers);
		}
	}
	
	//[r,c]에서 dir에 해당하는 부메랑을 만들 수 있는지 여부 반환
	static boolean isValid(int r, int c, int dir) {
		int nr1 = r + d[dir][0];
		int nc1 = c + d[dir][1];
		int nr2 = r + d[dir][2];
		int nc2 = c + d[dir][3];
		
		if(nr1 < 0 || nc1 < 0 || nr2 < 0 || nc2 < 0 || nr1 >= R || nc1 >= C || nr2 >= R || nc2 >= C) return false;
		if(visit[r][c] || visit[nr1][nc1] || visit[nr2][nc2]) return false;
		
		return true;
	}
	
	//[r,c]에서 dir 모양의 부메랑의 visit을 type으로 바꿈
	static void toggleVisit(int r, int c, int dir, boolean type) {
		int nr1 = r + d[dir][0];
		int nc1 = c + d[dir][1];
		int nr2 = r + d[dir][2];
		int nc2 = c + d[dir][3];
		
		visit[r][c] = type;
		visit[nr1][nc1] = type;
		visit[nr2][nc2] = type;
	}
}
