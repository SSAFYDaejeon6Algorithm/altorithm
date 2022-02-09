package bronze;

import java.util.Scanner;

public class B10870_fibonacci {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(fib(Integer.parseInt(sc.nextLine())));
		
	}
	
	static int fib(int n) {
		if(n == 1) return 1;
		if(n == 0) return 0;
		return fib(n-1) + fib(n-2);
	}
}
