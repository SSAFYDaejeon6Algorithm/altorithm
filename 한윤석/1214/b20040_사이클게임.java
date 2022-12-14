package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B20040_사이클게임 {

	static int n, m;
	static int parent [];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean isFind = false;
		String nm [] = br.readLine().split(" ");
		n = Integer.parseInt(nm[0]);
		m = Integer.parseInt(nm[1]);
		parent = new int [n];
		
		for(int i=0; i<n; i++) parent[i] = i;
		
		for(int i=0; i<m; i++) {
			String input [] = br.readLine().split(" ");
			int from = Integer.parseInt(input[0]);
			int to = Integer.parseInt(input[1]);
			
			if(isCycle(from, to)) {
				isFind = true;
				System.out.println(i+1);
				break;
			}
		}
		
		if(!isFind) System.out.println(0);
	}
	
	static int findSet(int a) {
		if(parent[a] == a) return a;
		return parent[a] = findSet(parent[a]);
	}
	
	static boolean isCycle(int from, int to) {
		int fRoot = findSet(from);
		int tRoot = findSet(to);
		
		if(fRoot == tRoot) return true;
		parent[tRoot] = fRoot;
		
		return false;
	}

}
