package bj;

import java.util.*;
import java.io.*;

public class p_2810 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int ans = 1;
		String ip = br.readLine();
		for (int n = 0; n < N; n++) {
			// couple seat
			if (ip.charAt(n) == 'L')
				n++;

			ans++;
		}

		System.out.println(N < ans ? N : ans);
	}
}
