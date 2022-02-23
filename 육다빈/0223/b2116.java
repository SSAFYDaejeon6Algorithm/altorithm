package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G2116_diceStacking {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] dice = new int[N][7];
		for(int n=0; n<N; n++) {
			char[] line = br.readLine().replace(" ", "").toCharArray();
			dice[n][line[0]-'0'] = line[5]-'0';
			dice[n][line[1]-'0'] = line[3]-'0';
			dice[n][line[2]-'0'] = line[4]-'0';
			dice[n][line[3]-'0'] = line[1]-'0';
			dice[n][line[4]-'0'] = line[2]-'0';
			dice[n][line[5]-'0'] = line[0]-'0';
		}
		
		int result = Integer.MIN_VALUE;
		for(int i=1; i<=6; i++) {	// 맨 밑 주사위 값 설정
			int under = i;
			int sum = 0;
			for(int n=0; n<N; n++) {// 주사위 별 옆면 최대값 저장
				int max = -1;
				for(int j=1; j<=6; j++) {
					if(j!=under && j!=dice[n][under]) max = Math.max(max, j);
				}
				sum += max;
				under = dice[n][under];
			}
			result = Math.max(result, sum);
		}
		System.out.println(result);
	}

}
