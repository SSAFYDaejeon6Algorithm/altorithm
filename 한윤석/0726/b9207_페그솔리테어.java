public class B9207_페그솔리테어 {
	
	static char map[][] = new char[5][9]; //맵 정보
	static int d[][] = {{1,0},{0,1},{-1,0},{0,-1}}; //수직, 수평
	static int min, step; //최소 핀 개수, 최소 이동 횟수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			min = Integer.MAX_VALUE;
			step = 0;
			
			for(int i=0; i<5; i++) map[i] = br.readLine().toCharArray();
			
			backtrack(0);
			
			System.out.println(min + " " + step);
			br.readLine();
		}
	}
	
	//cnt : 현재까지 이동 횟수
	static void backtrack(int cnt) {
		boolean flag = true; //이번 탐색에서 이동가능한 핀이 있는지 여부
		
		//<탐색>
		for(int i=0; i<5; i++) { //행
			for(int j=0; j<9; j++) { //열
				
				if(map[i][j] == 'o') { //핀인 경우에만 탐색
					
					for(int k=0; k<4; k++) { // [i,j]를 기준으로 4방 탐색
						if(!canSet(i,j,k)) continue; //<검사>
							
						//무조건 놓을 수는 있는 경우
						flag = false; //놓을 수 있는 핀이 있다고 체크
						int nr = i + d[k][0]; //다음 행
						int nc = j + d[k][1]; //다음 열
						int nnr = i + d[k][0] + d[k][0]; //다다음 행
						int nnc = j + d[k][1] + d[k][1]; //다다음 열
						
            //<진행>
						map[i][j] = '.';
						map[nr][nc] = '.';
						map[nnr][nnc] = 'o';
						
						backtrack(cnt+1); 
						
						map[i][j] = 'o';
						map[nr][nc] = 'o';
						map[nnr][nnc] = '.';
					} //end for k
				} //end if
			} //end for j
		} //end for i
		
		//움직일 수 있는 핀이 아무것도 없을 때
		if(flag) {
			int pinNum = 0; //남아있는 핀 수
			
			//남아있는 핀 수 세기
			for(int i=0; i<5; i++) 
				for(int j=0; j<9; j++) if(map[i][j] == 'o') pinNum++;
			
			//최소 핀수와 비교 후, 핀 수와 이동 횟수 갱신
			if(min > pinNum) {
				min = pinNum;
				step = cnt;
			}else if(min == pinNum) step = Math.min(step, cnt);
		}
	}
	
	static boolean canSet(int r, int c, int dir) {
		int nr = r + d[dir][0]; //다음 행
		int nc = c + d[dir][1]; //다음 열
		int nnr = r + d[dir][0] + d[dir][0]; //다다음 행
		int nnc = c + d[dir][1] + d[dir][1]; //다다음 열
		
		// 다음 좌표와 다다음 좌표 범위 검사
		if(nr < 0 || nc < 0 || nr >= 5 || nc >= 9 || nnr < 0 || nnc < 0 || nnr >= 5 || nnc >= 9) return false;
		
		// 다음 좌표가 핀인지, 다다음 좌표가 빈 칸인지 검사
		if(map[nr][nc] != 'o' || map[nnr][nnc] != '.') return false;
		
		return true;
	}
}
