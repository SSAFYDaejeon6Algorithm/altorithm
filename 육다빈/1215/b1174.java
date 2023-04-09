package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G1174_decreasigNumber {
	static int N, cnt=1;
	
	static long go(int n, int idx, double num) {
		if(idx==0) {
			if(++cnt==N) {
				return (long)num;
			}
			else return -11;
		}
		
		for(int i=0; i<n; i++) {
			long res = go(i, idx-1, num+i*Math.pow(10, idx-1));
			if(res!=-11) {
				return res;
			}
		}
		
		return -11;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		if(N==1) {
			System.out.println(0);
			return;
		}
		int idx=0;
		while(cnt!=N) {
			for(int i=1; i<=9; i++) {
				long res = go(i, idx, i*Math.pow(10, idx));
				if(res != -11) {
					System.out.println(res);
					return;
				}
			}
			if(++idx>1023) {
				System.out.println(-1);
				return;
			}
		}
		
	}

}
