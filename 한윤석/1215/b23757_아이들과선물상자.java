package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class B23757_아이들과선물상자 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nm [] = br.readLine().split(" ");
		String ca [] = br.readLine().split(" ");
		String wa [] = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		boolean isValid = true;
		PriorityQueue<Gift> gifts = new PriorityQueue<>();
		
		for(int i=0; i<N; i++)  gifts.add(new Gift(Integer.parseInt(ca[i])));
		
		for(int i=0; i<M; i++) {
			int amount = Integer.parseInt(wa[i]);
			Gift cur = gifts.poll();
			
			if(cur.n < amount) {
				isValid = false;
				break;
			}else {
				cur.n -= amount;
				gifts.add(cur);
			}
		}
		
		if(!isValid) System.out.println(0);
		else System.out.println(1);
	}

	static class Gift implements Comparable<Gift>{
		int n;
		public Gift(int n) {
			this.n = n;
		}
		@Override
		public int compareTo(Gift o) {
			return o.n - this.n;
		}
	}
}
