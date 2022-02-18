package algostudy;


import java.util.Scanner;


public class Solution_b2810_컵홀더 {

	public static void main(String[] args) {	
		int result = 0;
		
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); //좌석의 수 N
		sc.nextLine();
		String sentence = sc.nextLine();
		
		StringBuilder sb = new StringBuilder();
		sb.append("*");
				
		for (int i = 0; i < sentence.length(); i++) {
			if (sentence.charAt(i) == 'S') {
				sb.append("S*");
			}else if(sentence.charAt(i) == 'L') {
				sb.append("LL*");
				i++;
			}
		}
		
		char[] seat = new char[sb.length()];
		
		for (int i = 0; i < sb.length(); i++) {
			seat[i] = sb.charAt(i);
		}
		
		for (int i = 0; i < seat.length; i++) {
			if(seat[i] == '*' && i == (seat.length-1) ) //마지막 별에서 자꾸 문제가 생기는 경우를 차단
				break;
			if(seat[i] == '*') { //현재 칸이 *이라면
				seat[i] = 0; //현재칸을 0으로 저장
				seat[i+1] = 0; //다음칸을 0으로 저장
				result++;
			}
			else if(seat[i] != '*') { // 현재칸이 *이 아니라면
				if(seat[i] == 'L' || seat[i] == 'S') {// 현재칸이 L 또는 S라면
					if(seat[i+1] == '*') { //다음칸이 *이라면
						seat[i] = 0; //현재칸을 0으로 저장
						seat[i+1] = 0; //다음칸을 0으로 저장
						result++;
					}
					else { //현재칸이 *이 아니고, 현재칸이 L이나 S인데 다음칸에 *이 없다면
						seat[i] = 0;
					}
				}
			}
		}
		System.out.println(result);
	}
}
