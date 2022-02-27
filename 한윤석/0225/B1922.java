package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1922 {

	static int N,M; // 정점의 수, 간선의 수
	static ArrayList<Vertex> E []; //간선 정보 배열
	static boolean visit[]; //방문 여부
	static int minEdge[]; //i에게 닿는 최소 거리
	static int ans = 0; 
	
	static class Vertex{
		int no, w; //정점, 가중치
		public Vertex(int no, int w) {
			this.no = no;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		E = new ArrayList[N+1];
		visit = new boolean[N+1];
		minEdge = new int[N+1];
		
		for(int i=1; i<=N; i++) E[i] = new ArrayList<Vertex>();
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			E[from].add(new Vertex(to, w));
			E[to].add(new Vertex(from, w));
		}
		
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		
		prim(1);
		
		System.out.println(ans);
	}
	
	static void prim(int start) {
		minEdge[start] = 0; //시작 정점 초기화
		for(int i=1; i<=N; i++) {
			int min=Integer.MAX_VALUE, minV=0; //최소값과 그 정점
			for(int j=1; j<=N; j++) {
				if(!visit[j] && minEdge[j] < min) {
					min = minEdge[j];
					minV = j;
				}
			}
			
			visit[minV] = true;
			ans += min;
			
			for(Vertex v:E[minV]) {
				if(!visit[v.no] && minEdge[v.no] > v.w) minEdge[v.no] = v.w;
			}
		}
	}
}
