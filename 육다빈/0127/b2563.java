package bronze;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class B2563_colorPaper {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int SIZE = 100;
		int N = sc.nextInt();
		boolean[][] arr = new boolean[SIZE][SIZE];
		
		int x, y;
		for(int n=0; n<N; n++) {
			x = sc.nextInt()-1;
			y = sc.nextInt()-1;
			
			for(int i=x; i<x+10; i++) {		//색종이 붙이기
				for(int j=y; j<y+10; j++) {
					arr[i][j] = true;
				}
			}
			
		}
		
		int count = 0;
		for(int i=0; i<SIZE; i++) {			//붙은 면적 세기
			for(int j=0; j<SIZE; j++) {
				if(arr[i][j]) count++;
			}
		}
		
		System.out.println(count);

	}

}
