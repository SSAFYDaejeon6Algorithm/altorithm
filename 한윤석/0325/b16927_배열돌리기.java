
public class B16927_배열돌리기2 {

	static int M,N,R; //행, 열, 회전수
	static int m[][]; //맵 정보
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		m = new int[M][N];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) m[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int depth = Math.min(M/2, N/2); //배열이 생성되는 depth

		for(int i=0; i<depth; i++) rotate(i); //최외곽에서 depth만큼 떨어진 배열부터 회전 시작 
		
		for(int i=0; i<M; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j=0; j<N; j++) sb.append(m[i][j]).append(" ");
			System.out.println(sb);
		}
		System.out.println();
	}
	
	static void rotate(int depth) {
		int rot = R % ((M-depth*2)*2 + (N-depth*2)*2 - 4); // R % 제자리로 돌아오기 위한 회전수
		
		for(int r=0; r<rot; r++) {
			int t1 = m[depth][depth];
			int t2 = t1;
			
			//↓
			for(int i=depth+1; i<M-depth; i++) {
				t1 = m[i][depth];
				m[i][depth] = t2; 
				t2 = t1;
			}
			
			//→
			for(int i=depth+1; i<N-depth; i++) {
				t1 = m[M-depth-1][i];
				m[M-depth-1][i] = t2; 
				t2 = t1;
			}
			
			//↑
			for(int i=M-2-depth; i>=depth; i--) {
				t1 = m[i][N-1-depth];
				m[i][N-1-depth] = t2; 
				t2 = t1;
			}
			
			//←
			for(int i=N-2-depth; i>=depth; i--) {
				t1 = m[depth][i];
				m[depth][i] = t2; 
				t2 = t1;
			}
		}
	}
}
