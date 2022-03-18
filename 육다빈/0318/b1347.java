package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class S1347_makeMaze {

	static class Node{
		int i, j;
		Node(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		char[] line = br.readLine().toCharArray();
		
		int now_i=0, now_j=0;
		int min_i=Integer.MAX_VALUE, min_j=Integer.MAX_VALUE;
		int max_i=Integer.MIN_VALUE, max_j=Integer.MIN_VALUE;
		int d = 2; // 0:북, 1:동, 2:남, 3:서
		int[] di = {-1, 0, 1, 0};
		int[] dj = {0, 1, 0, -1};
		List<Node> list = new ArrayList<>();
		list.add(new Node(0, 0));
		for(char c: line) {
			switch (c) {
			case 'R': d = (d+1)%4;	break;
			case 'L': d = (d-1+4)%4;	break;
			case 'F': 
				list.add(new Node(now_i+di[d], now_j+dj[d]));
				min_i = Math.min(min_i, now_i+di[d]);
				min_j = Math.min(min_j, now_j+dj[d]);
				max_i = Math.max(max_i, now_i+di[d]);
				max_j = Math.max(max_j, now_j+dj[d]);
				break;
			}
		}
		int min = Math.min(min_i, min_j);
		char[][] map = new char[51][51];
		for(Node n: list) {
			map[n.i-min][n.j-min] = '.';
		}
		for(int i=0; i<=max_i-min; i++) {
			for(int j=0; j<=max_j-min; j++) {
				System.out.print(map[i][j]=='.' ? '.' : '#');
			}
			System.out.println();
		}
	}

}
