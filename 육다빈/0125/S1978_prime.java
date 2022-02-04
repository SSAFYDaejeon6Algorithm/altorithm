package silver;

import java.util.Scanner;

public class S1978_prime {
	public static boolean isPrime(int num) {
		int cnt = 0;
		if(num==1) return false;
		for(int i=2; i<num; i++) {
			if(num % i ==0)
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int cnt = 0;

		for(int i=0; i<N; i++) {
			if(isPrime(sc.nextInt())) 
				cnt++;
		}
		
		System.out.println(cnt);
		
		sc.close();
	}

}
