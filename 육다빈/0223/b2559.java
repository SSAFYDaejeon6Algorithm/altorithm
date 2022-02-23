package silver;

import java.util.Scanner;

public class S2559_sequence {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int sum = 0;
		int max = Integer.MIN_VALUE;
		int[] arr = new int[N];
		
		for(int n=0; n<N; n++) {
			arr[n] = sc.nextInt();
			
			if(n<K)	sum += arr[n];
			else	sum += arr[n] - arr[n-K];
			
			if(n>= K-1) max = Math.max(max, sum);
		}
		System.out.println(max);
	}

}
