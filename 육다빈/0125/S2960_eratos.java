package silver;

import java.util.Scanner;

public class S2960_eratos {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int cnt = 0;
		boolean[] isNotPrime = new boolean[N];  //기본값 false로 초기화
		
		for(int i=2; i<=N; i++) {
			if(isNotPrime[i-1]) continue;		//지운것 pass
			
			for(int j=i; j<=N; j+=i) {
				if(isNotPrime[j-1]) continue;	//지운것 pass
				
				isNotPrime[j-1] = true;
				if(++cnt == K) {
					System.out.println(j);
					break;
				}
			}
			if(cnt == K) break;
		}
		
	}

}
