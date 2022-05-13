package programmers;

import java.util.*;

public class p_86971 {
	public static void main(String[] args) {
		new Solution().solution(4, new int[][] {{1,2},{2,3},{3,4}});
	}
	
	// 전력망을 둘로 나누기
	static class Solution {
		ArrayList<ArrayList<Node>> graph;
		
		public int solution(int n, int[][] wires) {
	    	// make graph
	    	graph = new ArrayList<>();
	    	for(int i = 0; i <= n; i++)
	    		graph.add(new ArrayList<>());
	    	
	    	// input edges
	    	for(int i = 0; i < wires.length; i++) {
	    		int from = wires[i][0];
	    		int to = wires[i][1];
	    		
	    		graph.get(from).add(new Node(from, to));
	    		graph.get(to).add(new Node(to, from));
	    	}
	    	
	    	// get answer
	    	int answer = Integer.MAX_VALUE;
	    	for(int i = 0; i < wires.length; i++)
	    		answer = Math.min(answer, Math.abs(n - 2 * getNodeEA(wires[i][0], wires[i][1])));
	        return answer;
	    }
	    
	    
		public int getNodeEA(int from, int to) {
	    	int ret = 1;
	    	// BFS
	    	Queue<Node> q = new LinkedList<>();
	    	boolean visited[] = new boolean[graph.size()];
	    	visited[from] = true;
	    	
	    	for(int i = 0; i < graph.get(from).size(); i++) {
	    		Node next = graph.get(from).get(i);
	    		
	    		if(next.to == to)
	    			continue;
	    		visited[next.to] = true;
	    		q.add(next);
	    	}
	    	
	    	while(!q.isEmpty()) {
	    		Node n = q.poll();
	    		ret++;
	    		
	    		for(int i = 0; i < graph.get(n.to).size(); i++) {
	    			Node next = graph.get(n.to).get(i);
	    			
	    			if(!visited[next.to]) {
	    				visited[next.to] = true;
	    				q.add(next);
	    			}
	    		}
	    	}
	    	return ret;
	    }
	    
		class Node {
	    	int from, to;
	    	public Node(int from, int to) {
	    		this.from = from;
	    		this.to = to;
	    	}
	    }
	}
}
