package baekjoon;

import java.util.Scanner;

public class B25215_타이핑 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int dp[][] = new int [s.length()+1][s.length()+1]; // i번째에서 [0] 마름모 활성화로 남겨둘 때 , [1] 비활성화로 남겨둘 때 타이핑 수
		
		if(Character.isUpperCase(s.charAt(0))) {
			dp[0][0] = 2;
			dp[0][1] = 2;
		}else {
			dp[0][0] = 2;
			dp[0][1] = 1;
		}
		
		for(int i=1; i<s.length(); i++) {
			boolean isUpper = Character.isUpperCase(s.charAt(i));
			if(isUpper) {
				//마름모 켠 상태로 넘기려면, 직전에 켜져있던 상태에서 숫자 하거나 꺼진 상태에서 마름모+숫자
				dp[i][0] = Math.min(dp[i-1][0]+1, dp[i-1][1]+2); 
				//마름모 끈 상태로 넘길려면, 직전에 켜져있던 상태에서 숫자+마름모 하거나 꺼진 상태에서 별+숫자
				dp[i][1] = Math.min(dp[i-1][0]+2, dp[i-1][1]+2); 
			}else {
				//마름모 켠 상태로 넘기려면, 직전에 켜져있던 상태에서 별+숫자 하거나 꺼진 상태에서 마름모+숫자
				dp[i][0] = Math.min(dp[i-1][0]+2, dp[i-1][1]+2); 
				//마름모 끈 상태로 넘길려면, 직전에 켜져있던 상태에서 마름모+숫자 하거나 꺼진 상태에서 숫자
				dp[i][1] = Math.min(dp[i-1][0]+2, dp[i-1][1]+1); 
			}
		}
		System.out.println(Math.min(dp[s.length()-1][0], dp[s.length()-1][1]));
	}
}
