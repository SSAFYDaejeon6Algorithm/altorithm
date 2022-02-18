package algostudy;

import java.util.Scanner;

public class Solution_b8320_직사각형을만드는방법 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int result = 0; //처음부터 (1*n) 전부 넣고 시작
		
		int sum = 0;
		int cnt = 0;
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if(i == j && i*j <= n)  //정사각형일 경우
					result++;
				if(i != j && i*j <= n) { //직사각형일 경우
					result++;
					cnt++; //겹치는 수 반으로 나누려고 만든 변수, 조건에 맞는 건 전부 ++한다.
				}
			}
		}
		
		System.out.print(result-(cnt/2));
		// 1*1 하나는 이미 넣어져 있으니까 하나 빼고 
		// 2차 배열을 보면  2*3과 3*2가 같은 것이므로 해당 갯수를 빼야한다
	}

}
