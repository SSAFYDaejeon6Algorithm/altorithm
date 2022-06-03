package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G17298_NGE {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 입력
		int N = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		int num[] = new int[N];
		for(int i=0; i<N; i++) num[i] = Integer.parseInt(s[i]);
		
		// 2. 각 배열마다 해당 NGE의 인덱스 계산
		int[] NGE_idx = new int[N];
		NGE_idx[N-1] = -1;

		for(int i=N-2; i>=0; i--) {
			int p = i+1;
			while(p!=-1) {
				if(num[i]<num[p]) {
					NGE_idx[i] = p;
					break;
				}else {
					p = NGE_idx[p];
				}
			}
			if(p==-1) NGE_idx[i] = -1;
		}
		
		// 3. 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			if(NGE_idx[i]==-1) sb.append("-1");
			else sb.append(num[NGE_idx[i]]);
			if(i<N-1) sb.append(" ");
		}
		System.out.println(sb);
	}
}
