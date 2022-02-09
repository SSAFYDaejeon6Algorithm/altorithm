package silver;

import java.util.Scanner;

public class S1182_subsequence {
	static int N;
	static int S;
	static int cnt = 0;	
	static int[] num;
	static boolean[] select;
	
	/* 부분집합 함수 : 재귀를 통해 각 원소를 순차적으로 넣었다 빼면서 모든 부분집합 생성
	 * - n : 원소 index
	 * - sum : 앞서 선택된 원소들의 합
	 */ 
	static void subset(int n, int sum) {
		
		select[n] = true;	//num[n]을 포함할 경우
		if(n == N-1) {
			cnt += sum+num[n]==S ? 1 : 0;
		}else {
			subset(n+1, sum+num[n]);
		}

		select[n] = false;	//num[n]을 제외할 경우
		if(n == N-1) {
			cnt += sum==S ? 1 : 0;
		}else {
			subset(n+1, sum);
		}
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = sc.nextInt();
		
		num = new int[N];
		select = new boolean[N];
		
		for(int n=0; n<N; n++) {
			num[n] = sc.nextInt();
		}
		
		if(S==0) cnt--;		//공집합 제외
		subset(0, 0);
		
		System.out.println(cnt);
		
	}


}
