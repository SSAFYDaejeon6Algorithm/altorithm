package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G20056_sharkFireball {
	static int[] di = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dj = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[][] dir = {{1, 3, 5, 7}, {0, 2, 4, 6}};
 	static class Ball{ // ball의 질량, 속도, 방향 저장
		int m, s, d;
		Ball(int m, int s, int d){
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	static class Point{
		int i, j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<Ball>[][][] balls = new ArrayList[N][N][K+2]; // ball이 담긴 이차원행렬을, k번째별로 만들어둠
		Queue<Point> queue = new LinkedList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) balls[i][j][0] = new ArrayList<Ball>();
		}
		for(int a=0; a<M; a++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken())-1;
			int j = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if(balls[i][j][0].size()==0) queue.add(new Point(i, j));
			balls[i][j][0].add(new Ball(m, s, d));
		} // input end
		
		int k = 0;
		int result = 0;
		while(k<=K) {
			result = 0;
			for(int i=0; i<N; i++) { // k번째 이차원행렬 모두 초기화
				for(int j=0; j<N; j++) balls[i][j][k+1] = new ArrayList<Ball>();
			}
			int size = queue.size(); // 현재 map(balls)중에 ball이 들어있는 칸 수
			for(int q=0; q<size; q++) {
				Point now = queue.poll();
				if(balls[now.i][now.j][k].size()==1) { // 해당 칸에 공이 하나만 들어있는 경우
					Ball b = balls[now.i][now.j][k].get(0);
					int ni = (now.i +di[b.d]*b.s +N)%N;
					int nj = (now.j +dj[b.d]*b.s +N)%N;
					if(balls[ni][nj][k+1].size()==0) queue.add(new Point(ni, nj));
					balls[ni][nj][k+1].add(new Ball(b.m, b.s, b.d));
					result += b.m;
				}else {	// 해당 칸에 두개이상의 공이 담겨있는 경우
					int sum_m = 0;
					int sum_s = 0;
					int pre_dir = -1; // 이전 방향의 홀짝 여부 (0이면 짝, 1이면 홀)
					int isDirEq = 1;  // 모든합이 홀짝이면 0, 아니면  1 저장
					for(Ball b : balls[now.i][now.j][k]) { // 공들의 합 계산
						sum_m += b.m;
						sum_s += b.s;
						if(pre_dir==-1) pre_dir = b.d%2;
						else if(isDirEq==1 && pre_dir!=b.d%2) isDirEq = 0;
					}
					int div_m = sum_m/5;
					int div_s = sum_s/balls[now.i][now.j][k].size();
					if(div_m!=0) { // 볼 배분해서 다음 배열에 보내두기
						for(int d : dir[isDirEq]) {
							int ni = (now.i + di[d]*div_s + N)%N;
							int nj = (now.j + dj[d]*div_s + N)%N;
							if(balls[ni][nj][k+1].size()==0) queue.add(new Point(ni, nj));
							balls[ni][nj][k+1].add(new Ball(div_m, div_s, d));
							result += div_m;
						}
					}
				}
			}
			k++;
		}
		System.out.println(result);
	}

}
