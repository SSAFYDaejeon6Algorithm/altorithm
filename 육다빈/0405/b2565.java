package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class G2565_wire {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] wire = new int[N][2];
		int[] lis = new int[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<2; j++) wire[i][j] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(wire, Comparator.comparingInt(o1->o1[0])); //제발이거외워라;
		
		lis[0] = wire[0][1];
		int result = 0;
		for(int i=1; i<N; i++) {	// i : 현재 B전기줄의 번호
			for(int j=0; j<N; j++) { // j : 앞서 연결된 전기줄 수. lis[j] : 연결된 전기줄 중 가장 뒷번호(B)
				if(wire[i][1]<lis[j] || lis[j]==0) {
					lis[j] = wire[i][1];
					result = Math.max(result, j);
					break;
				}
			}
		}
		System.out.println(N-result-1);
	}

}
