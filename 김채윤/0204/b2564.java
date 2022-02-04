//3.경비원 : https://www.acmicpc.net/problem/2564

package b220204;

import java.util.Scanner;

public class b2564 {

	static int x,y; // 동근이의 위치 
	static int w,h; // 직사각형 블록
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		w = sc.nextInt(); // 너비
		h = sc.nextInt(); // 높이
		int n = sc.nextInt();
		
		// 동근이의 위치까지 파악하기위해 n+1을 하였다 / x,y 값 저장
		int[][]store = new int[n+1][2];
		for(int i=0;i<store.length;i++) {
			int dir = sc.nextInt(); // 북남서동
			int far = sc.nextInt(); // (좌,상)얼마나 멀어져 있는지
			switch(dir) {
			// 행과열 -> 배열처럼 값을 넣었다
			case 1: //북
				store[i][0]=0;
				store[i][1]=far;
				break;
			case 2: //남
				store[i][0]=h;
				store[i][1]=far;
				break;
			case 3: //서
				store[i][0]=far;
				store[i][1]=0;
				break;
			case 4: //동
				store[i][0]=far;
				store[i][1]=w;
				break;
			}
		}
		
		// 가장 마지막에 경비원의 위치가 있음
		x = store[n][0];
		y = store[n][1];
		
		int sum=0;
		for(int i=0;i<n;i++) {
			int c=clock(store[i][0],store[i][1]);
			//시계방향으로 갈 경우 , 반시계방향으로 갈 경우
			sum+=Math.min(c, (w+h)*2-c);
		}
		System.out.println(sum);
		
	}
	
	public static int clock(int i,int j) {
		int cnt =0;
		// 북,남에 있을 경우
		if(Math.abs(x-i)==h) {
			cnt = h + j + y;
		}
		// 서,동에 있을 경우
		else if(Math.abs(y-j)==w){
			cnt = w + i + x;
		}
		else {
			cnt = Math.abs(x-i)+Math.abs(y-j);
		}
		return cnt;
	}
}