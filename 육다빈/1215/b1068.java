package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G1068_tree {
	static int N, root, remove;
	static List<Integer>[] childs;
	static boolean visit[];
	
	static int go(int root) {
		if(root==remove) return 0;
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(root);
		visit[root] = true;
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int s=0; s<size; s++) {
				int now = queue.poll();
				int tmp=0;
				for(int c : childs[now]) {
					if(c != remove) {
						queue.add(c);
						tmp++;
					}
					visit[c] = true;
				}
				if(tmp==0) cnt++;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		childs = new List[N];
		for(int i=0; i<N; i++) childs[i] = new ArrayList<Integer>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			if(parent==-1) root = i;
			else childs[parent].add(i);
		}
		
		remove = Integer.parseInt(br.readLine());
	
		visit = new boolean[N];
		System.out.println(go(root));
	}

}
