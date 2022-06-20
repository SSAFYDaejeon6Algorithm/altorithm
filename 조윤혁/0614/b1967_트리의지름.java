import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, lastNode, resultSum;
	static List<Node> list[];
	static boolean[] visited;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	N = parse(br.readLine());
    	list = new ArrayList[N+1];
    	visited = new boolean[N+1];
    	
    	for(int i=0; i<N+1; i++) {
    		list[i] = new ArrayList<>();
    	}
    	
    	for(int i=0; i<N-1; i++) {
    		st = new StringTokenizer(br.readLine());
    		int parent = parse(st.nextToken());
    		int child = parse(st.nextToken());
    		int cost = parse(st.nextToken());
    		list[parent].add(new Node(child, cost));
    		list[child].add(new Node(parent, cost));
    	}
    	
//    	for(int i=1; i<=N; i++) {
//    		for(Node node : list[i])
//    			System.out.print ("[" +node.v + " : " + node.cost+"]");
//    		System.out.println();
//    	}
    	
    	visited[1] = true; // 어떤 노드가 들어와도 된다.
    	dfs(1, 0); // lastNode를 구하기 위함, 어떤 노드가 들어가도 최대의 가중치의 끝 노드를 찾게 됨
    	
    	visited = new boolean[N+1]; // 초기화
    	resultSum = 0; // 두점사이 누적된 가중치
    	visited[lastNode] = true; 
    	dfs(lastNode, 0); // lastNode에서 최대의 가중치의 끝 노드 찾기
    	
    	System.out.println(resultSum);
    	
    }
    
    
	private static void dfs(int currV, int costSum) {
		if(resultSum < costSum) {
			resultSum = costSum;
			lastNode = currV;
		}
		
		for(Node nextV : list[currV]) {
			if(visited[nextV.v]) continue;
			visited[nextV.v] = true;
			dfs(nextV.v, costSum + nextV.cost);
		}
	}


	private static int parse(String str) {
		return Integer.parseInt(str);
	}
    
	private static class Node{
		int v, cost;
		Node(int v, int cost){
			this.v = v;
			this.cost = cost;
		}
	}
    
}