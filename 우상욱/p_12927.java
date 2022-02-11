package bj;

import java.util.*;
import java.io.*;

public class p_12927 {

	static boolean[] switches;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String ip = br.readLine();
		
		// inputs
		switches = new boolean[ip.length()+1];
		for(int i = 0; i < ip.length(); i++)
			if(ip.charAt(i) == 'Y')
				switches[i+1] = true;
		
		int ans = 0;
		for(int i = 1; i < switches.length; i++) {
			// 'N'
			if(!switches[i])
				continue;
			
			// 'Y'
			ans++;
			for(int j = i; j < switches.length; j += i)
				switches[j] = !switches[j];
		}
		
		System.out.println(ans);
	}
}
