package bronze;

import java.util.Scanner;

public class B8320_makeRectangle {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int sum = 1; 				// 초기 값 : 정사각형 개수가 1 일때
		for(int n=2; n<=N; n++) {	// 정사각형의 개수를 늘려가며 직사각형 생성
			for(int i=1; i<=Math.sqrt(n); i++) {
				if(n%i==0) {		// "정사각형 개수 = 직사각형 크기 = 두 자연수의 곱"임을 이용
					sum++;
				}
			}
		}
		
		System.out.println(sum);
	}

}
