package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B1966_프린터큐 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			String [] NM = br.readLine().split(" ");
			int N = Integer.parseInt(NM[0]);
			int M = Integer.parseInt(NM[1]);
			String [] nums = br.readLine().split(" ");
			Queue<Num> q = new LinkedList<>();
			int target = Integer.parseInt(nums[M]);
			int ans = 1;
			
			for(int i=0; i<N; i++) {
				q.add(new Num(i, Integer.parseInt(nums[i])));
			}
			
			outer:while(!q.isEmpty()) {
				int size = q.size();
				boolean flag = true;
				int maxNum = 0;
				int maxIdx = 0;
				
				for(int i=0; i<size; i++) {
					Num num = q.poll();
					if(maxNum < num.n) {
						maxNum = num.n;
						maxIdx = i;
					}
					if(num.n > target) flag = false;
					q.add(num);
				}
				
				if(flag) { //타겟보다 큰게 없었음
					for(int i=0; i<size; i++) {
						Num num = q.poll();
						
						if(num.n == target) {
							if(num.i == M) {
								System.out.println(ans);
								break outer;
							}else {
								ans++;
							}
						}else {
							q.add(num);
						}
					}
				}else {
					for(int i=0; i<maxIdx; i++) q.add(q.poll());
					q.poll();
					ans++;
				}
			}
		}
	}
	
	static class Num{
		int i,n;
		public Num(int i, int n) {
			this.i = i;
			this.n = n;
		}
	}

}
