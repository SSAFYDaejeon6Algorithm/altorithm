package bj;

import java.util.*;
import java.io.*;

public class p_1992 {

	static int video[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// inputs
		video = new int[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < line.length(); j++)
				video[i][j] = line.charAt(j) - '0';
		}

		StringBuilder sb = new StringBuilder();
		compact(0, 0, N, sb);
		
		// print result
		System.out.println(sb);
	}

	static void compact(int x, int y, int n, StringBuilder sb) {
		// check all pixel is same
		boolean isAllSame = true;
		for (int i = x; i < x + n; i++)
			for (int j = y; j < y + n; j++)
				if (video[i][j] != video[x][y])
					isAllSame = false;

		if (isAllSame)
			sb.append(video[x][y]);
		else {
			// not all is same
			sb.append("(");
			compact(x, y, n / 2, sb);
			compact(x, y + n / 2, n / 2, sb);
			compact(x + n / 2, y, n / 2, sb);
			compact(x + n / 2, y + n / 2, n / 2, sb);
			sb.append(")");
		}
	}
}
