package silver;

import java.util.Scanner;

public class S2477_melonField {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int melon = sc.nextInt();		//면적당 참외 수
		int[][] field = new int[6][2];	//index 0:변의 방향, 1:변의 길이
		
		for(int i=0; i<6; i++) {
			field[i][0] = sc.nextInt();
			field[i][1] = sc.nextInt();
		}
		
		int large=0, small=0;
		for(int i=0; i<6; i++) {	//변의 방향이 같은 것이 연속으로 나오는 경우 (1 3 1 3 이런식)
			if(field[i][0]==field[(i+2)%6][0] && field[(i+1)%6][0]==field[(i+3)%6][0]) {
				large = (i+4)%6;	//외부 직사각형의 첫 index
				small = (i+1)%6;	//내부 직사각형의 첫 index
				break;
			}
		}
		
		large = field[large][1]*field[(large+1)%6][1];	//외부 직사각형 넓이
		small = field[small][1]*field[(small+1)%6][1];	//내부 직사각형 넓이
		
		System.out.println(melon*(large-small));
		
	}

}
