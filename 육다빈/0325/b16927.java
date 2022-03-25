package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G16927_turnArray2 {

	public static void main(String[] args) throws IOException {
		int[] di = {1, 0, -1, 0};
		int[] dj = {0, 1, 0, -1};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int M = Integer.parseInt(line[1]);
		int R = Integer.parseInt(line[2]);
		
		int[][] arr = new int[N][M];
		int[][] res = new int[N][M];
		for(int i=0; i<N; i++) {
			line = br.readLine().split(" ");
			for(int j=0; j<M; j++) arr[i][j] = Integer.parseInt(line[j]);
		}
		N--; M--;
		int depth = 0;
		while(N > 0 && M > 0) {
			int length = 2*(N+M);
			int[] ni = new int[length];
			int[] nj = new int[length];
			int n = 0;
			for(int d=0; d<4; d++) {
				int K = ((d%2==0) ? N : M);
				for(int k=0; k<K; k++) {
					ni[n] = di[d];
					nj[n++] = dj[d];
				}
			}
			
			int sum_i = 0, sum_j=0;
			int r = 0;
			int realR = R % (length);
			while(r < realR) {
				sum_i += ni[r];
				sum_j += nj[r++];
			}
			int now_i = depth, now_j = depth;
			for(int d=0; d<length; d++) {
				res[now_i+sum_i][now_j+sum_j] = arr[now_i][now_j];
				sum_i += ni[r] - ni[(r-realR+length)%length];
				sum_j += nj[r] - nj[(r-realR+length)%length];
				r = (r+1)%(length);
				now_i += ni[d];
				now_j += nj[d];
			}
			N -= 2;
			M -= 2;
			depth++;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<res.length; i++) {
			for(int j=0; j<res[i].length; j++) {
				sb.append(res[i][j]);
				if(j!=res[i].length-1) sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
