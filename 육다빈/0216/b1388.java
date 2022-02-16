package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1388_floorDecoration {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int M = Integer.parseInt(line[1]);
		
		String[][] floor = new String[N][M];
		boolean[][] isChecked = new boolean[N][M];
		for(int n=0; n<N; n++) {
			floor[n] = br.readLine().split("");
		}
		
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(isChecked[i][j]) continue;
				
				if(floor[i][j].equals("-")) {	// case 1. '-' 장식인 경우
					for(int m=j+1; m<M; m++) {
						if(isChecked[i][m] || floor[i][m].equals("|")) break;
						isChecked[i][m] = true;
					}
				}else {
					for(int n=i+1; n<N; n++) {	// case 2. '|' 장식인 경우
						if(isChecked[n][j] || floor[n][j].equals("-")) break;
						isChecked[n][j] = true;
					}
				}
				isChecked[i][j] = true;
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
