//백준 2309번. 일곱 난쟁이
//새로 이용한 것 : 
//1.중첩for문 탈출을 위해 Loop 지정해준 후 break Loop;로 한번에 탈출
//2.Arrays.sort(배열이름) = 오름차순!
package b220126;

import java.util.Arrays;
import java.util.Scanner;

public class b2309 {
	public static void main(String[] args) {

		int sum = 0; // 전체 키를 다 더할 sum
		int[] height = new int[9]; // 배열크기 9칸

		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 9; i++) {
			height[i] = sc.nextInt();
			sum += height[i];
		}

		Loop1: for (int i = 0; i < 9; i++) {
			Loop2: for (int j = 0; j < 9; j++) {
				if (i != j) {
					if (sum - (height[i] + height[j]) == 100) {
						height[i] = 0;
						height[j] = 0;
						break Loop1;
					}
				}
			}
		}
		Arrays.sort(height); // Arrays.sort() = 오름차순정렬

		for (int i = 0; i < 9; i++) {
			if (height[i] > 0)
				System.out.println(height[i]);
		}
	}

}