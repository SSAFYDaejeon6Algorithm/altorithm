package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2491_sequence {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int len = Integer.parseInt(br.readLine());
		String[] line = br.readLine().split(" ");
		int now, pre=0;
		int minus=1, plus=1, minusMax=1, plusMax=1;
		
		for(int i=0; i<len; i++) {
			now = Integer.parseInt(line[i]);
			if(i > 0) {
				if(now > pre) {				//1.이전숫자보다 클 경우
					plus++;
					if(minusMax < minus) {
						minusMax = minus;
					}
					minus = 1;
				}else if(now < pre) {		//2.이전숫자보다 작을경우
					minus++;
					if(plusMax < plus) {
						plusMax = plus;
					}
					plus = 1;
				}else {						//3.이전숫자와 동일할경우
					plus++;
					minus++;
				}
			}
			pre = now;
		}
		System.out.println(Math.max(Math.max(plus, plusMax), Math.max(minus, minusMax)));
	}

}
