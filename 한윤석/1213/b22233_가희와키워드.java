package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class B22233_가희와키워드 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String NM = br.readLine();
		int N = Integer.parseInt(NM.split(" ")[0]);
		int M = Integer.parseInt(NM.split(" ")[1]);
		Map<String, Integer> map = new HashMap<>();
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			map.put(s, 1);
		}
		
		for(int i=0; i<M; i++) {
			String [] keywords = br.readLine().split(",");
			
			for(int j=0; j<keywords.length; j++) {
				String keyword = keywords[j];
				if(map.containsKey(keyword)) map.remove(keyword);
			}
			System.out.println(map.size());
		}
	}

}
