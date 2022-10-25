package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G2250_treesHW {
	static int N;
	static int[][] node;
	static int[] cols;
	
	static int dfs(int now, int cnt) {
		if(now==-1) return cnt;         // 존재하지 않는 노드 pass
		
		int c = dfs(node[now][0], cnt); // 1. 왼쪽 서브트리 열 번호 부여
		cols[now] = c;					        // 2. 현재 노드 열 번호 부여
		return dfs(node[now][1], ++c);	// 3. 오른쪽 서브트리 열 번호 부여
	}
	
	static String bfs(int startNode) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(startNode);
		int max_width=-1, max_level=-1, now_level=0;
		
		while(!queue.isEmpty()) {
			now_level++;
			
			Object[] tmp = queue.toArray(); // 1. 현재 레벨 너비 계산
			int now_width = cols[(int)tmp[tmp.length-1]] - cols[(int)tmp[0]] + 1; 
			if(max_width < now_width) {
				max_width = now_width;
				max_level = now_level;
			}
			
			int size = queue.size();	    	// 2. 다음 레벨 노드 수집
			for(int i=0; i<size; i++) {
				int now = queue.poll();
				if(node[now][0] != -1) queue.add(node[now][0]);
				if(node[now][1] != -1) queue.add(node[now][1]);
			}
		}
		return max_level + " " + max_width;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		node = new int[N+1][2];
		cols = new int[N+1];
		
		// 1. 노드 저장
		boolean[] isChild = new boolean[N+1];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int now = Integer.parseInt(st.nextToken());
			for(int j=0; j<2; j++) {
				node[now][j] = Integer.parseInt(st.nextToken());
				if(node[now][j] != -1) isChild[node[now][j]] = true;
			}
		}
		
		// 2. 루트노드 찾기
		int root = -1;
		for(int i=1; i<=N; i++) {
			if(!isChild[i]) {
				root = i;
				break;
			}
		}
		
		// 3. dfs로 순서대로 열 번호 주기
		dfs(root, 1);
		
		// 4. bfs로 레벨 별 너비 구하기
		System.out.println(bfs(root));
	}

}
