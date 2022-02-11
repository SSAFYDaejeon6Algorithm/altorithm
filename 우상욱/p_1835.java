package bj;

import java.util.*;
import java.io.*;

public class p_1835 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		Deque<Integer> dq = new LinkedList<>();
		for(int i = N; i > 0; i--) {
			dq.addFirst(i);
			
			// flip card
			for(int j = 0; j < i; j++)
				dq.addFirst(dq.pollLast());
		}
		
		// print result
		StringBuilder sb = new StringBuilder();
		for(int n : dq)
			sb.append(n).append(" ");
		System.out.println(sb.toString());
	}
}
