import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//백준 1261 알고스팟 골드4 bfs ver. 
public class B1261_3 {

	static int R,C;
	static int m[][]; //맵정보
	static boolean visit[][]; //방문여부
	static int d [][] = {{1,0},{0,1},{-1,0},{0,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		m = new int[R][C];
		visit = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) m[i][j] = s.charAt(j)-'0';
		}
			
		bfs();
		
		System.out.println();
	}
	
	static void bfs() {
		visit[0][0] = true;
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		pq.add(new Pos(0,0,0));
		
		while(!pq.isEmpty()) {
			Pos cur = pq.poll();

			//목적지에 도달했으면
			if(cur.r == R-1 && cur.c == C-1) {
				System.out.println(cur.w);
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nr = cur.r + d[i][0];
				int nc = cur.c + d[i][1];
				
				if(nr < 0 || nc < 0 || nr >= R || nc >= C || visit[nr][nc]) continue;
				
				visit[nr][nc] = true;
				pq.add(new Pos(nr,nc,cur.w + m[nr][nc])); //m[nr][nc]가 0이면 그대로, 1이면 더해져서 넣음
			}
			
		}
	}
	
	static class Pos implements Comparable<Pos>{
		int r,c,w;
		public Pos(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}
		@Override
		public int compareTo(Pos o) {
			return this.w - o.w;
		}
	}
}
