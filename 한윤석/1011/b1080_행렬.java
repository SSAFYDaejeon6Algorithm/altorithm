public class B1080_행렬 {
	
	static int R,C;
	static int map[][], mine[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int [R][C];
		mine = new int[R][C];
		int ans = 0;
		
		//input
		for(int i=0; i<R; i++) {
			char [] input = br.readLine().toCharArray();
			for(int j=0; j<C; j++) mine[i][j] = input[j] - '0' == 0 ? -1 : 1;
		}
		for(int i=0; i<R; i++) {
			char [] input = br.readLine().toCharArray();
			for(int j=0; j<C; j++) map[i][j] = input[j] - '0' == 0 ? -1 : 1;
		}
		
		for(int i=0; i<=R-3; i++) {
			for(int j=0; j<=C-3; j++) {
				if(mine[i][j] == map[i][j]) continue;
				
				// 3x3 행렬 반전
				for(int k=i; k<i+3; k++) {
					for(int l=j; l<j+3; l++) mine[k][l] *= -1;
				}
				
				ans++;
			}
		}
		
		if(isSame()) System.out.println(ans);
		else System.out.println(-1);
	}
	
	static boolean isSame() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(mine[i][j] != map[i][j]) return false;
			}
		}
		
		return true;
	}
}
