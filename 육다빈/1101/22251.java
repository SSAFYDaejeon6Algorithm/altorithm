package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G22251_villainHS {
	static int N, K, P, X;
	static int[] n = new int[10];
	static int[] origin;
	static int result = 0;
	
	static void dfs(int num, int idx, int cnt) { //num=현재 숫자, idx=바꿀 자리수, cnt=누적 반전 횟수
		if(idx < 0 || cnt==P) {
			if(num>0) result++;
			return;
		}
		System.out.println();
		for(int j=0; j<10; j++) {
			if(origin[idx]==j) continue;
			char[] diff = Integer.toBinaryString(n[origin[idx]] ^ n[j]).toCharArray(); // 현재 숫자와 바꿀 숫자의 차이를 이진수로 변환
			
			int charge = 0; // 반전 비용 계산
			for(char c : diff) {
//				System.out.print(c + " ");
				charge += c - '0';
			}
			if(cnt+charge > P) continue;
			System.out.print("자리수 " + idx + ", " + j + "로 변환 시작, ");
			System.out.println("반전 비용 = " + charge);
			
			int addedNum = (int) (Math.pow(10, idx)*j); // 숫자 한계 점검
			if(num+addedNum<=N) dfs(num+addedNum, idx-1, cnt+charge); 
			else break;
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
			
		// 1. 0~9까지 상태값 저장
		n[0] = 0B1111110;
		n[1] = 0B0110000;
		n[2] = 0B1101101;
		n[3] = 0B1111001;
		n[4] = 0B0110011;
		n[5] = 0B1011011;
		n[6] = 0B1011111;
		n[7] = 0B1110000;
		n[8] = 0B1111111;
		n[9] = 0B1111011;
		
		origin = new int[K];
		for(int k=0; k<K; k++) origin[k] = (int) ((X / Math.pow(10, k)) % 10); // 현재 층의 각 자리수별 숫자 저장
		
		dfs(0, K-1, 0);
		
		System.out.println(result);
		
	}

}
