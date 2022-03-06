package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 20364 부동산 다툼 실버2
public class B20364 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 땅의 개수
		int Q = Integer.parseInt(st.nextToken()); //오리 수
		boolean [] visit = new boolean[N+1]; //방문 여부
		
		for(int i=0; i<Q; i++) {
			int want = Integer.parseInt(br.readLine()); //원하는 땅
			int min = Integer.MAX_VALUE; //방문한 땅 중 최소 노드
			
			for(int j=want; j>=1; j/=2) if(visit[j]) min = j; //want부터 1까지의 경로 중 방문한 땅 있다면 최소 노드 갱신
			
			if(min == Integer.MAX_VALUE) { //방문한 땅 하나도 없었다면
				System.out.println("0");
				visit[want] = true;
			}else System.out.println(min); //방문한 땅 있었으면
		}
	}
}