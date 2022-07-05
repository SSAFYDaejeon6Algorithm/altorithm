package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G2660_pickAChairman {

	static int N;
	static List<Integer>[] child;
	static boolean[] visit;
	
	static int bfs(int n) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(n);
		visit[n] = true;
		int result = -1;
		while(!queue.isEmpty()) {
			result++;
			int size = queue.size();
			for(int i=0; i<size; i++) {
				int now = queue.poll();
				for(Integer ch : child[now]) {
					if(!visit[ch]) {
						visit[ch] = true;
						queue.add(ch);
					}
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		child = new List[N+1];
		for(int i=0; i<=N; i++) child[i] = new ArrayList<Integer>();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a==-1) break;
			child[a].add(b);
			child[b].add(a);
		}
		StringBuilder sb = new StringBuilder();
		int min = Integer.MAX_VALUE, cnt = 0;
		for(int i=1; i<=N; i++) {
			visit = new boolean[N+1];
			int now = bfs(i);
			if(min > now) {
				cnt = 1;
				min = now;
				sb = new StringBuilder(i + " ");
			}else if(min == now) {
				cnt++;
				sb.append(i).append(" ");
			}
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.insert(0, min + " " + cnt + "\n"));
	}

}
