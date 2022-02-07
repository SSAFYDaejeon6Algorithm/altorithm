package silver;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class S2578_bingo {
	public static class Location {
		public int x;
		public int y;
		
		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//숫자판 저장 : <숫자, 숫자가 저장된 x,y좌표>
		Map<String, Location> num = new HashMap<>();	
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				num.put(sc.next(), new Location(i, j));
			}
		}
		
		Location loc;
		int[] bingo = new int[12];			//bingo가 될 수 있는 줄들(가로5세로5대각선2)
		Loop : for(int i=0; i<25; i++) {	//불러주는 숫자 하나씩 체크
			loc = num.get(sc.next());
											//해당 숫자가 포함된 줄들 체크
			bingo[loc.x]++;					//가로 빙고	
			bingo[loc.y+5]++;				//세로 빙고
			
			if(loc.x==loc.y) bingo[10]++;	//대각선 빙고
			if(loc.x+loc.y==4) bingo[11]++;	//역대각 빙고
			
			int cnt = 0;
			for(int b=0; b<12; b++) {		//5개가 채워진 줄 개수 체크
				if(bingo[b] == 5) cnt++;
				if(cnt >= 3) {
					System.out.println(i+1);
					break Loop;
				}
			}
		}
	}		
}
