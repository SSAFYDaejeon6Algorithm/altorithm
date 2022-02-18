package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S1448_makeTriangle {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		for(int n=0; n<N; n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);	// 빨대 길이 순으로 정렬
		
		comb(0, N-1);		// 길이가 가장 긴 빨대부터 선택
		
		System.out.println(max);
	}
	
	static int N;
	static int max = -1;
	static char back = 'n';
	static int[] arr;
	static int[] tri = new int[3];
	
	/* 가장 긴 빨대들부터 선택하여 삼각형 만드는 메소드 (조합 사용)
	 * - n : 선택된 빨대 수
	 * - start : 선택 시작할 빨대 인덱스
	 */
	static void comb(int n, int start) {
		if((back=='y' && n > 0) || back=='Y') return;
		back = 'n';
		
		if(n==3) {
			if(tri[0]>=tri[1]+tri[2]) back = 'y';	// 삼각형 조건만족 X : 선택 빨대중 가장 긴 빨대 제거
			else {
				max = Math.max(max, tri[0] + tri[1] + tri[2]);	// 삼각형 조건 만족 시 즉시종료
				back = 'Y';
			}
			return;
		}
		
		for(int i=start; i>=0; i--) {
			tri[n] = arr[i];
			comb(n+1, i-1);
			comb(n, i-1);
		}
	}
}
