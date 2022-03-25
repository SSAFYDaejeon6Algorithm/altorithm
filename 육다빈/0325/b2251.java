package silver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class S2251_bucket {

	static int[] max;
	static Map<Integer, List<Integer>> map = new HashMap<>();

	static void selectBuckets(int[] buc) {
		if(!map.containsKey(buc[0])) map.put(buc[0], new ArrayList<Integer>(buc[2]));
		else if(map.get(buc[0]).contains(buc[2])) return;
		else map.get(buc[0]).add(buc[2]);
		
		for(int i=0; i<3; i++) {	// i : 물을 버리는 물통
			for(int j=0; j<3; j++) {// j : 물을 받는 물통
				if(i == j) continue;
				int[] tmp = {buc[0], buc[1], buc[2]};
				if(tmp[i]+tmp[j] > max[j]) { // 물을 부으면 받는 물통의 물이 넘칠 경우
					tmp[i] = tmp[i]+tmp[j] - max[j];
					tmp[j] = max[j];
				}else {	// 물을 부어도 받는 물통의 물이 넘치지 않는 경우
					tmp[j] += tmp[i];
					tmp[i] = 0;
				}
				selectBuckets(tmp);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		max = new int[3];
		for(int i=0; i<3; i++) max[i] = sc.nextInt();
		
		int[] start = {0, 0, max[2]};
		selectBuckets(start);
		
		Collections.sort(map.get(0));
		StringBuilder sb = new StringBuilder();
		for(int n : map.get(0)) sb.append(n).append(" ");
		
		System.out.println(sb.toString().trim());
	}

}
