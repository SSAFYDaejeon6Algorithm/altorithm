//백준 2605번. 줄 세우기
//새로 이용한 것 :
//1.List와 친해지십시오
package b220126;

import java.util.ArrayList;
import java.util.Scanner;

public class b2605 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<>();
		int N = sc.nextInt(); //학생 수 입력받기
		
		for(int i=0; i < N; i++) {
			int num = sc.nextInt();//입력받은 값이 0이라면
			
			if(num == 0) //(움직이지않음)
				list.add(i+1); //입력받은 값이 0이라면, 원래 형식은 add(index, value)인데 index가 생략되어있으니까
							   //value값에 학생 번호를 i+1 부여. 가장 앞에있던 숫자는 N이므로 쓸모가 없음
			else //(움직임)
				list.add(list.size()-num, i+1);//입력받은 값이 0이 아니면, 그 값만큼 index이동, 그리고 학생번호 i+1 부여  
		}
		
		for(int i : list)
			System.out.print(i+" ");
	}

}
