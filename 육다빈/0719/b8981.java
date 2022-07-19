package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G8981_inputNumber {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		
		int[] result = new int[N];
		int idx = 0;
		for(int i=0; i<N; i++) {
			while(result[idx]!=0) idx = (idx+1)%N;
			result[idx] = Integer.parseInt(str[i]);
			idx = (idx+result[idx])%N;
		}
		
		System.out.println(Arrays.toString(result).replace("[", "").replace(",", "").replace("]", ""));
	}
	
}
