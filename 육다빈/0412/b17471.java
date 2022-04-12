package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G17471_gerryMandering {

	static int N, result = Integer.MAX_VALUE;
	static int[] visit, gu;
	static List<Integer>[] linkedGu;
	static List<Integer> setA, setB;
	
	static void subset(int n) {
		if(n>N) {
			setA = new ArrayList<>();
			setB = new ArrayList<>();
			for(int i=1; i<=N; i++) {
				if(visit[i]==1) setA.add(i);
				else setB.add(i);
			}
			if(setA.size()==0 || setB.size()==0 || 
					countLinkedGu(setA, 1) + countLinkedGu(setB, 2) != N) return;
			int sumA = 0, sumB = 0;
			for(int i=1; i<=N; i++) {
				if(visit[i]==1) sumA += gu[i];
				else sumB += gu[i];
			}
			result = Math.min(result, Math.abs(sumA-sumB));
			return;
		}
		visit[n] = 1;
		subset(n+1);
		visit[n] = 2;
		subset(n+1);
	}
	
	static int countLinkedGu(List<Integer> set, int n) {
		int cnt = 1;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(set.get(0));
		while(!queue.isEmpty()) {
			int now = queue.poll();
			set.remove((Object)now);
			for(int li : linkedGu[now]) {
				if(set.contains(li)) {
					set.remove((Object)li);
					cnt++;
					queue.add(li);
				}
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		gu = new int[N+1];
		visit = new int[N+1];
		for(int i=1; i<=N; i++) gu[i] = Integer.parseInt(st.nextToken());
		
		linkedGu = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			linkedGu[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			for(int j=0; j<len; j++) {
				linkedGu[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		subset(1);
		System.out.println(result==Integer.MAX_VALUE ? -1 : result);
	}

}
