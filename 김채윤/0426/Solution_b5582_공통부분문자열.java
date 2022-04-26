package algorithm;

import java.util.*;
import java.io.*;

public class Solution_b5582_공통부분문자열 {
	public static int ans;
	public static String st1, st2;
	public static int[][] sp; //st1과 st2가 입력되었을 때, 공통되는 부분 문자열 처리용 2차원 배열
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st1 = br.readLine();
		st2 = br.readLine();
		
		sp = new int[st2.length() + 1][st1.length() + 1];
		
		for (int i = 0; i < st2.length(); ++i) {
			for (int j = 0; j < st1.length(); ++j) {				
				if (st2.charAt(i) == st1.charAt(j)) {
					sp[i+1][j+1] = sp[i][j] + 1;
					ans = (ans < sp[i+1][j+1]) ? sp[i+1][j+1] : ans;
				}
			}
		}
		System.out.println(ans);
	}
}