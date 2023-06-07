package bj;

import java.util.*;
import java.io.*;

public class p_1080 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int source[][] = new int[N][M];
		int target[][] = new int[N][M];
		int compared[][] = new int[N][M];
	
		String str;
		// input source array
		for(int i = 0; i < N; i++) {
			str = br.readLine();
			for(int j = 0; j < M; j++) {
				source[i][j] = str.charAt(j) - '0';
			}
		}
		
		// input target array
		for(int i = 0; i < N; i++) {
			str = br.readLine();
			for(int j = 0; j < M; j++) {
				target[i][j] = str.charAt(j) - '0';
			}
		}
		
		// compare array
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				compared[i][j] = target[i][j] != source[i][j]? 1 : 0;
			}
		}
		
		// too small array to flip
		if(N < 3 || M < 3) {
			if(!isValid(compared)) {
				System.out.println(-1);
				return;
			}
		}
		
		// filp
		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0;j < M; j++) {
				if(compared[i][j] == 1) {
					filp_3x3(compared, i, j);
					ans++;
				}
			}
		}
		
		if(isValid(compared))
			System.out.println(ans);
		else
			System.out.println(-1);
	}
	

	static int filp_3x3(int arr[][], int x,int  y) {
		if(arr.length -1 < x + 2 || arr[0].length - 1 < y + 2)
			return -1;
		
		for(int i = x; i < x + 3; i++) {
			for(int j = y; j < y + 3; j++) {
				arr[i][j] = arr[i][j] == 1? 0 : 1;
			}
		}
		return 1;
	}
	
	static boolean isValid(int arr[][]) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				if(arr[i][j] == 1)
					return false;
			}
		}
		return true;
	}
}
