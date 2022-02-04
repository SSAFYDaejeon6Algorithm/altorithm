//2. 방 배정 : https://www.acmicpc.net/problem/13300
package b220204;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b13300 {
	static int arr[][];
	static int N, K, S, Y;
	static int count;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[2][7];
		count = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			
			arr[S][Y]++;
		}
		
		for (int i = 0; i < 2; i++) {
			for (int j = 1; j <= 6; j++) {
				if (arr[i][j] == 0) continue;
				
				if (arr[i][j] % K != 0) count++;
				count += arr[i][j] / K;
			}
		}
		
		System.out.println(count);
	}
}