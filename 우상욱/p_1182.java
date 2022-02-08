package bj;

import java.util.Scanner;

public class p_1182 {
	
	static int[] nums;
	static int ans, N, S;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); S = sc.nextInt();
		
		// inputs
		nums = new int[N];
		for(int i = 0; i < N; i++)
			nums[i] = sc.nextInt();
		
		for(int i = 0; i < N; i++)
			recur(i, nums[i]);

		System.out.print(ans);
	}
	
	static void recur(int i, int sum) {
		// dont escape for other cases
		if(sum == S)
			ans ++;
		
		for(int j = i + 1; j < N; j++)
			recur(j, sum + nums[j]);
	}
}
