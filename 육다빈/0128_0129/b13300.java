package bronze;

import java.util.Scanner;

public class B13300_room {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int stdNum = sc.nextInt();
		int roomMax = sc.nextInt();
		int[][] stdList = new int[2][6];
		
		for(int s=0; s<stdNum; s++) {
			stdList[sc.nextInt()][sc.nextInt()-1]++;
		}
		
		int needRoom = 0;
		for(int[] student : stdList) {
			for(int s : student) {
				needRoom += (s % roomMax == 0) ? s/roomMax : s/roomMax+1;
			}
		}
		System.out.println(needRoom);
	}

}
