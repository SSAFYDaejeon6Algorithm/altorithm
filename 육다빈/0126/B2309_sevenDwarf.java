package bronze;

import java.util.Arrays;
import java.util.Scanner;

public class B2309_sevenDwarf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		int[] arr = new int[9];
		for(int i=0; i<9; i++) {
			arr[i] = sc.nextInt();
			sum += arr[i];
		}
		
		int[] notDwarf = {0, 0};		// 난쟁이 7명 골라내기
		for(int i=0; i<8; i++) {
			for(int j=i+1; j<9; j++) {
				if(sum-(arr[i]+arr[j]) == 100) {
					notDwarf[0] = i;
					notDwarf[1] = j;
					break;
				}
			}
		}

		int[] result = new int[7];		// 정렬을 위한 7명 배열 생성
		int idx = 0;
		for(int i=0; i<9; i++) {
			if(i != notDwarf[0] && i != notDwarf[1]) {
				result[idx++] = arr[i];
			}
		}
		
		Arrays.sort(result);
		for(int num : result) {
			System.out.println(num);
		}
		
	}

}
