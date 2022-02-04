package bronze;

import java.util.Scanner;

public class B2605_lineUp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sNum = sc.nextInt();
		int target = -1;
		int tmp = 0;
		int[] line = new int[sNum];
		
		for(int i=0; i<sNum; i++) {			// 뽑기 전 줄 먼저 세워두기
			line[i] = i+1;
		}
		
		for(int i=0; i<sNum; i++) {
			target = i-sc.nextInt();		// 이동할 자리
			// 본인이 이동할 자리에 있는게 아니라면
			if(line[target] != i+1) {		// 조건식 이거 말고 sc.nextInt==0 이 더 나을수도
				tmp = line[i];
				for(int j=i-1; j>=target; j--) {
					line[j+1] = line[j];
				}
				line[target] = tmp;
			}
		}
		
		for(int n : line) {
			System.out.print(n+" ");
		}
	}

}
 