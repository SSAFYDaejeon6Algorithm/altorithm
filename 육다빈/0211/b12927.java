package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S12927_multiplesSwitch {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] bulb = br.readLine().split("");
		
		int cnt=0;
		for(int i=0; i<bulb.length; i++) {
			if(bulb[i].equals("N")) continue;
			for(int j=i+1; j<=bulb.length; j += i+1) {
				bulb[j-1] = bulb[j-1].equals("N") ? "Y" : "N";
			}
			cnt++;
		}
		
		System.out.println(cnt);
		
	}

}
