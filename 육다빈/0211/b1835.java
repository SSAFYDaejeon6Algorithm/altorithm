package silver;

import java.util.LinkedList;
import java.util.Scanner;

public class S1835_card {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		LinkedList<Integer> card = new LinkedList<>();
		
		// 1. 카드 역순으로 삽입
		card.add(N);
		for(int i=N-1; i>0; i--) {
			card.addFirst(i);
			for(int j=0; j<i; j++) {
				card.addFirst(card.getLast());
				card.removeLast();
			}
		}
		
		// 2. 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(card.get(i));
			if(i!=N-1) sb.append(" ");
		}
		System.out.println(sb);
	}

}
