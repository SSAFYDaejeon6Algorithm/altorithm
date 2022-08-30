vpackage gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G16928_snackAndLadder {
	static int[] go = new int[101];
	static boolean[] visit = new boolean[101];

	static int bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		visit[1] = true;
		int cnt = 0;
		while(!queue.isEmpty()) {
			cnt++;
			int size = queue.size();
			for(int i=0; i<size; i++) {
				int now = queue.poll();
				for(int j=1; j<=6; j++) {
					if(now+j<100 && !visit[now+j]) {
						int n = now+j;
						while(go[n]!=0) {
							visit[n] = true;
							n = go[n];
						}
						visit[n] = true;
						queue.add(n);
					}
					else if(now+j==100) return cnt;
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N+M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			go[from] = to;
		}
		
		System.out.println(bfs());
	}

}
