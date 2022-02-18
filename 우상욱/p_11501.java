package bj;

import java.util.*;
import java.io.*;

public class p_11501 {

	static int bought_one[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());

			// inputs
			int stock_values[] = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				stock_values[i] = Integer.parseInt(st.nextToken());
			
			int max = stock_values[N - 1]; 	// last stock
			long profit = 0;				// 64 bit integer

			for (int i = N - 2; i >= 0; i--) {
				if(stock_values[i] > max)
					max = stock_values[i];
				else
					profit += max - stock_values[i];
			}

			// print result
			StringBuilder sb = new StringBuilder();
			sb.append(profit).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}

	}
}
