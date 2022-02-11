package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G17281_baseball {
	static int N;
	static int sum;
	static int max = 0;
	static boolean[] ground = new boolean[3];
	static boolean[] selected = new boolean[9];
	static int[] order = new int[9];
	static int[][] ining;
	
	/* 정해진 타석순대로 게임을 진행
	 * - in : 이닝 인덱스
	 * - n : 이닝 시작 시 첫번째로 들어가는 타석index
	 */
	static void playGame(int in, int n) { 
		ground[0]=false; ground[1]=false; ground[2]=false;
		
		if(in==N) {
			max = Math.max(max, sum);
			return;
		}
		
		int out = 0, score;
		while(out < 3) {
			score = ining[in][order[n]];
			if(score==0) {		//case1. 아웃
				out++;
			}else { 
				//출루한 선수 체크
				for(int i=2; i>=0; i--) {	
					if(ground[i]) {
						if(i+score > 2) {
							sum++;
						}else {
							ground[i+score] = true;
						}	
						ground[i]=false;
					}
				}
				if(score==4) {	//case2. 홈런
					sum++;
				}else {			//case3. 안타
					ground[score-1]=true;
				}
			}
			n = (n+1)%9;
		}
		playGame(in+1, n);
	}
	
	/* 모든 타석의 경우를 순열로 계산
	 * - n : 선수를 채울 타석 번호
	 */
	static void permutation(int n) {
		if(n==3) {	// 4번타자는 내정됨
			order[n] = 0;
			selected[0] = true;
			permutation(n+1);
			return;
		}
		if(n==9) {	// 모든 타석 선발완료
			sum = 0;
			playGame(0, 0);
			return;
		}
		
		// 순열 생성
		for(int i=1; i<9; i++) {
			if(!selected[i]) {
				selected[i] = true;
				order[n]=i;
				permutation(n+1);
				
				selected[i] = false;
			}
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		ining = new int[N][9];
		
		for(int i=0; i<N; i++) {
			ining[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		order[3] = 0;
		permutation(0);
		
		System.out.println(max);
		
	}

}
