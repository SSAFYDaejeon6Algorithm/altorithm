package gold;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class G2448_star11 {
	static Queue<Integer>[] line;
	
	static class Node{
		int i, j;
		Node(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	static void star(int i, int j, int h) {	// (i, j) : 삼각형의 맨 위 꼭지점 좌표. h:삼각형의 높이
		if(h==3) {
			for(int n=0; n<3; n++) {
				for(int m=j-n; m<=j+n; m++) {
					if(n==1 && m==j) continue;
					line[i+n].add(m);
				}
			}
			return;
		}
		h /= 2;
		star(i, j, h);
		star(i+h, j-h, h);
		star(i+h, j+h, h);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		line = new Queue[N];
		for(int i=0; i<N; i++) line[i] = new LinkedList<>();
		
		star(0, N-1, N); // 별 좌표 수집
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			int pre_star = 0;
			while(pre_star <= N*2) {
				int next_star = line[i].isEmpty() ? N*2 : line[i].poll(); // 다음 별이 없으면 끝까지 공백출력
				for(int j=pre_star; j<next_star; j++) sb.append(" ");	// 공백 출력
				if(next_star != N*2) sb.append("*");					// 별 출력
				pre_star = next_star+1;
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
