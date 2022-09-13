package bj;

import java.io.*;
import java.util.*;

public class p_3649 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String check_eof = "";
		
		while((check_eof = br.readLine()) != null) {
			int x = Integer.parseInt(check_eof) * 10000000;
			int n = Integer.parseInt(br.readLine());
			
			// input lego length
			int legos[] = new int[n];
			for(int i = 0; i < n; i++)
				legos[i] = Integer.parseInt(br.readLine());
			
			Arrays.sort(legos);
			
			int l1 = 0, l2 = n - 1;
			boolean isCorrect = false;
			while(l1 < l2) {
				int sum = legos[l1] + legos[l2];
				if(sum == x) {
					isCorrect = true;
					sb.append("yes ").append(legos[l1]).append(" ").append(legos[l2]).append("\n");
					break;
				} 
				
				int not_used = (sum < x? l1++ : l2--);
			}
			
			if(!isCorrect) sb.append("danger\n");
			check_eof = null;
		}
		System.out.println(sb);
	}
}
