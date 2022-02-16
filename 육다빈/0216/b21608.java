package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S21608_SharkElementarySchool {

	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 1. 학생 정보 입력
		int[][] student = new int[N*N+1][4];
		int[] list = new int[N*N];
		
		for(int n=0; n<N*N; n++) {
			String[] line = br.readLine().split(" ");
			int num = Integer.parseInt(line[0]);
			list[n] = num;
			for(int i=0; i<4; i++) {
				student[num][i] = Integer.parseInt(line[i+1]);
			}
		}
		
		// 2. 자리 배치
		int[][] seat = new int[N][N];
		
		for(int l : list) {
			int maxScore = Integer.MIN_VALUE;
			int tmp_i =-1, tmp_j=-1;
			
			for(int j=0; j<N; j++) {	// 우선순위 순으로 (행j -> 열i) 탐색
				for(int i=0; i<N; i++) {
					if(seat[i][j]!=0) continue;	// 이미 배정된 자리 제외
					int[] score = {0, 0};
					
					for(int d=0; d<4; d++) {	// 사방 탐색
						int now_i = i + di[d];
						int now_j = j + dj[d];
						if(now_i<0 || now_i>=N || now_j<0 || now_j>=N) continue;
						
						if(seat[now_i][now_j]==0) {	// case 1. 인접자리가 비어있는 경우
							score[1]++;
							continue;
						}
						for(int k=0; k<4; k++) {	// case 2. 인접자리에 좋아하는 학생이 있는지 탐색
							if(seat[now_i][now_j]==student[l][k]) {
								score[0]++;
								break;
							}
						}
					}
					int totalScore = score[0]*5+score[1]*1;	// 해당 자리의 우선순위 점수
					if(maxScore < totalScore) {
						maxScore = totalScore;
						tmp_i = i;
						tmp_j = j;
					}
				}
			}
			seat[tmp_i][tmp_j] = l;	// 자리 배정 완료
		}
		
		// 3. 만족도 조사 후 출력
		int sum = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int count = 0;
				for(int d=0; d<4; d++) {	// 사방 탐색
					int now_i = i + di[d];
					int now_j = j + dj[d];
					if(now_i<0 || now_i>=N || now_j<0 || now_j>=N) continue;
					
					for(int k=0; k<4; k++) {	// 인접자리에 좋아하는 학생이 있는지 탐색
						if(seat[now_i][now_j] == student[seat[i][j]][k]) {
							count++;
							break;
						}
					}
				}
				sum += count==0 ? 0 : Math.pow(10, count-1);
			}
		}
		System.out.println(sum);
	}

}
