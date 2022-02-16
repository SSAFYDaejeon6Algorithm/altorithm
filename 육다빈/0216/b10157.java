package silver;

import java.util.Scanner;

public class S10157_seatAssignment {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int C = sc.nextInt();
		int R = sc.nextInt();
		int K = sc.nextInt();
		
		int[] di = {1, 0, -1, 0};
		int[] dj = {0, 1, 0, -1};
		
		int num = 1;
		int i = 1;
		int j = 1;
		
		if(K == num) {
			System.out.println("1 1");
			return;
		}
		
		if(C*R < K) {	// 관객이 들어갈 자리가 없는 경우
			System.out.println(0);
			return;
		}
		
		int[] len = {R-1, C-1, R-1, C-2};
		
		loop : while(true) {
			for(int d=0; d<4; d++) {
 				
				for(int l=0; l<len[d]; l++) {	// 사이클 길이만큼 이동
					i += di[d];
					j += dj[d];
					if(++num == K) {	
						System.out.println(j + " " + i);
						break loop;
					}
				}
				if(i==R && j==1) len[d]--;	// 처음 사이클 첫 방향부분만 개수 예외처리
				else len[d] -= 2;
			}
		}
	}

}
