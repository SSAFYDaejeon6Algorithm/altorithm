package gold;

import java.util.Scanner;

public class G9663_NQueen {
	
	static int N;
	static int cnt = 0;
	static int[] isQueen;
	
	static void setQueen(int n) {
		if(notQueen(n-1)) return;

		if(n > N) {
			cnt++;
			return;
		}
		for(int i=1; i<=N; i++) {
			isQueen[n] = i;
			setQueen(n+1);
		}
	}
	
	static boolean notQueen(int n) {
		for(int i=1; i<n; i++) {
			if(isQueen[i]==isQueen[n] || n-i==Math.abs(isQueen[n]-isQueen[i])) return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		isQueen = new int[N+1];
		
		setQueen(1);
		
		System.out.println(cnt);
	}
}
