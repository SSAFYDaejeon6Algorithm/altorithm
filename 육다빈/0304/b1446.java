package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class S1446_shortcut {

	static int N, D, result=Integer.MAX_VALUE;
	static int[][] road;
	
	static void go(int start, int len) {
		if(result <= len) return;
		if(start == D) {
			result = Math.min(result, len);
			return;
		}
		for(int i=0; i<N; i++) {
			if(start <= road[i][0]) go(road[i][1], len+(road[i][0]-start)+road[i][2]);
		}
		if(start < D) go(D, len+(D-start));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		D = Integer.parseInt(line[1]);
		road = new int[N][3];
		
		for(int i=0; i<N; i++) {
			line = br.readLine().split(" ");
			for(int j=0; j<3; j++) road[i][j] = Integer.parseInt(line[j]);
		}
		
		Arrays.sort(road, Comparator.comparingInt(o1 -> o1[0]));
		go(0, 0);
		System.out.println(result);
	}

}
