import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] paper = new int[1001][1001]; //종이가 그려진 정보
		int N = sc.nextInt(); //testcase
		int [][] info = new int [N][4];
		for (int i = 0; i < N; i++) { // loop: testcase
			int row = sc.nextInt(); //시작 행
			int col = sc.nextInt(); //시작 열
			int width = sc.nextInt(); //너비
			int height = sc.nextInt(); //높이
			info[i][0]=row;
			info[i][1]=col;
			info[i][2]=width;
			info[i][3]=height;
			for (int j = row; j < row + width; j++) {
				for (int k = col; k < col + height; k++) {
					paper[j][k]=i; // 색칠 영역을 i값으로 설정
				}
			}
		}
		
		for (int i=0; i<N; i++) {
			int sum =0; //영역 넓이의 합
			int row = info[i][0]; 
			int col = info[i][1]; 
			int width = info[i][2]; 
			int height = info[i][3];
			for(int j=row; j<row+width; j++) { //선택 영역 조회
				for(int k=col; k<col+height; k++) {
					if(paper[j][k]==i) // 중복으로 덮힌 부분은 index보다 큰 값이므로
						sum++;
				}
			}
			System.out.println(sum);
		}
	}
}
