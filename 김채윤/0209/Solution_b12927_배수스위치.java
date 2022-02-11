package algostudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_b12927_배수스위치 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		String[] arr = str.split("");

		int switchnum = 0; // 스위치 조작 횟수

		for (int i = 0; i < arr.length; i++) { // 배열의 메인 위치를 뜻할 숫자
			// [i]가 Y일 경우
			if (arr[i].equals("Y")) { 
				switchnum++; //스위치 조작 + 1
				//본격적인 연산 시작
				for (int j = 0; j < (arr.length / (i+1)); j++) { // 곱해줄 숫자
					if (arr[i + ((i + 1) * j)].equals("Y"))
						arr[i + ((i + 1) * j)] = "N";
					else //equals.("N")일 경우
						arr[i + ((i + 1) * j)] = "Y";
				}
			}
		}
		//만약 스위치 조작수가 배열길이보다 커진다면 = 만약, 모든 전구를 끌 수 없다면 -1 출력
		if(switchnum > arr.length)
			System.out.println("-1");
		else
			System.out.println(switchnum);
		
	}
}