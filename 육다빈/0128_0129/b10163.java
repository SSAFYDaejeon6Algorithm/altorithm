package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10163_colorPaper2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int loc[][] = new int[N][4];			
		int[] paper = new int[N];							//각 색종이의 넓이
		boolean[][] area = new boolean[1001][1001];
		
		for(int n=0; n<N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			loc[n][0] = Integer.parseInt(st.nextToken());	//x
			loc[n][1] = Integer.parseInt(st.nextToken());	//y
			loc[n][2] = Integer.parseInt(st.nextToken());	//width
			loc[n][3] = Integer.parseInt(st.nextToken());	//height
		}
		
		for(int n=N-1; n>=0; n--) {							//가장 마지막에 들어온 색종이부터 넓이 계산
			paper[n] = loc[n][2] * loc[n][3];
			for(int i=loc[n][1]; i<loc[n][1]+loc[n][3]; i++) {
				for(int j=loc[n][0]; j<loc[n][0]+loc[n][2]; j++) {
					if(area[i][j] == false) {				//위에 쌓인 색종이가 없을경우
						area[i][j] = true;	
					}else {									//있으면 전체 넓이 감소
						paper[n]--;						
					}
				}
			}
		}
		for(int p : paper) {
			System.out.println(p);
		}
	}
}
