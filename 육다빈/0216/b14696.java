package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B14696_ttakji {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		
		for(int n=0; n<N; n++) {
			// 1. 딱지 정보 입력받기
			List<Integer> cardA = new ArrayList<>();
			List<Integer> cardB = new ArrayList<>();
			
			List[] card = {cardA, cardB};
			for(List c : card) {
				String[] line = br.readLine().split(" ");
				for(int i=1; i<line.length; i++) {
					c.add(Integer.parseInt(line[i]));
				}
			}
			
			// 2. 딱지 비교 후 결과 출력
			boolean[] winner = new boolean[2];
			for(int i=4; i>0; i--) {
				winner[0] = false;
				winner[1] = false;
				if(cardA.contains(i)) winner[0] = true;
				if(cardB.contains(i)) winner[1] = true;
				
				if(winner[0]!=winner[1]) {
					System.out.println(winner[0] ? "A" : "B");
					break;
				}else {
					if(!winner[0]) {
						if(i==1) System.out.println("D");
						continue;
					}
					cardA.remove((Object)i);
					cardB.remove((Object)i++);
				}
			}
		}
	}

}
