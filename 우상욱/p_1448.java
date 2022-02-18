package bj;

import java.util.*;
import java.io.*;

public class p_1448 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// inputs
		int straws[] = new int[N];
		for (int i = 0; i < N; i++)
			straws[i] = Integer.parseInt(br.readLine()) * -1;

		Arrays.sort(straws);
		
		for (int i = 0; i < N - 2; i++) {
			// three sides
			int a = straws[i] * -1;
			int b = straws[i + 1] * -1;
			int c = straws[i + 2] * -1;
			
			if(a < b + c) {
				System.out.println((a + b + c) + "");
				return;
			}			
		}
		
		System.out.println(-1 + "");
	}
}
