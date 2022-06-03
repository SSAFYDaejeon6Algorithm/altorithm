package algorithm;

import java.io.*;
import java.util.*;

public class Solution_b17298_오큰수 {

	public static void main(String[] args) throws Exception {
		
		//입력
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(reader.readLine());
		
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		int[] arr = new int[a];
		
		//토막내서 넣기
		for (int i = 0; i < a; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> s = new Stack<>();
		int[] ans = new int[a];
		
		StringBuilder sb = new StringBuilder();
		
		//각각의 위치선정 + 조건
		for (int i = a - 1; i >= 0; i--) {
			while (!s.isEmpty() && s.peek() <= arr[i]) {
				s.pop();
			}
			if (s.isEmpty()) {
				ans[i] = -1;
			} else {
				ans[i] = s.peek();
			}
			s.push(arr[i]);
		}
		for (int k : ans) {
			sb.append(k + " ");
		}
		System.out.print(sb.toString());
	}
}
