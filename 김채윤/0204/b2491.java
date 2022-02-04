//4.수열 : https://www.acmicpc.net/problem/2491 
package b220204;
import java.util.Scanner;

public class b2491 {
	static int N;
	static int answer = 0;
	static int[] input;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		input = new int[N];

		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		//오름차순-가장 
		int asc =1;
		for (int i = 0; i < N-1; i++) {
						
			if (input[i] <= input[i + 1]) {
				asc ++;	
			}
			else {
				asc =1;
			}
			answer = Math.max(asc, answer);//Math.max로 두 값을 비교해서 큰거 출력
		}
		// 내림차순
		int desc =1;
		for (int i = 0; i < N-1; i++) {
						
			if (input[i] >= input[i + 1]) {
				desc ++;
				
			}
			else {
				desc =1;
			}
			answer = Math.max(desc, answer); //Math.max로 두 값을 비교해서 큰거 출력
			
		}
		// answer 중 가장 큰거
		System.out.println(answer);
	}
}