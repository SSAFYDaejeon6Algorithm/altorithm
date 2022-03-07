package silver;

import java.util.Scanner;

public class S20364_realEstateDispute {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int Q = sc.nextInt();
		
		boolean[] visit = new boolean[N+1];
		
		for(int q=0; q<Q; q++) {
			int now = sc.nextInt();
			int stop_point = -1;
			
			if(visit[now]) stop_point = now;	// 목적지가 이미 점유된 경우
			else visit[now] = true;
			
			while(now > 0) {	// 목적지부터 역으로 막힐 부분 탐색
				if(now%2 == 0) now /= 2;
				else now = (now-1)/2;
				
				if(visit[now]) stop_point = now;
			}
			System.out.println(stop_point==-1 ? 0 : stop_point);
		}
	}

}
