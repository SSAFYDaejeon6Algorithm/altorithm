package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S2527_rectangle {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int n=0; n<4; n++) {
			// rt : x1 y1 p1 q1, x2 y2 p2 q2 순서대로 저장
			int[] rt = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int x1=0, y1=1, p1=2, q1=3, x2=4, y2=5, p2=6, q2=7;
			int cnt = 0;
			
			//case 1: 두 직사각형이 만나지 않는 경우
			if(rt[p1]<rt[x2] || rt[p2]<rt[x1] || rt[q1]<rt[y2] || rt[q2]<rt[y1]) {
				System.out.println("d");
				continue;
			}
			/*x좌표, y좌표끼리 같은지 세어보기*/
			if(rt[x1]==rt[p2])	cnt++;
			if(rt[q1]==rt[y2])	cnt++;
			if(rt[p1]==rt[x2])	cnt++;
			if(rt[y1]==rt[q2])	cnt++;
			
			//case2 : 한 변에서 닿는 경우
			if(cnt == 1) { 
				System.out.println("b");
				continue;
			//case3 : 한 점에서 닿는 경우
			}else if(cnt == 2) {
				System.out.println("c");
			//case4 : 두 직사각형이 겹쳐지는 경우
			}else {
				System.out.println("a");
			}
		
		}
	}

}
