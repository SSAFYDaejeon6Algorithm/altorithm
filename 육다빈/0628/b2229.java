package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G2229_makeGroup {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] df = new int[N], num = new int[N];
		
		String[] ch = br.readLine().split(" ");
		num[0] = Integer.parseInt(ch[0]);
		int result = 0;
	
		for(int i=1; i<N; i++) {
			num[i] = Integer.parseInt(ch[i]);
			int min=num[i], max=num[i];
			df[i]=df[i-1];
			for(int j=i-1; j>=0; j--) {
				min = Math.min(min, num[j]);
				max = Math.max(max, num[j]);
				df[i] = Math.max(((j==0)?0:df[j-1])+max-min, df[i]);
			}
			result = Math.max(result, df[i]);
		}
		System.out.println(result);
	}
}
