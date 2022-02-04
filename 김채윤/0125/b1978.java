//백준 b1978. 소수 찾기
// 소수의 조건 : 1과 자기자신을 제외하고 다른 수로 나누면 항상 나머지가 1 이상 존재
package b220125;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class b1978 {
 
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		br.readLine();	
		
		int yesprime = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine()," "); // " " 쪼개기
		
		while(st.hasMoreTokens()) { // StringTokenizer 클래스 객체에서 다음에 읽어 들일 token이 있으면 true, 없으면 false를 return
									// 소수인경우 true, 아닌경우 false  
									
			boolean amiprime = true; //기본값은 true로 fix
			int num = Integer.parseInt(st.nextToken()); 
			
			if(num == 1) { //1은 소수가 아님
				continue;
			}
			for(int i = 2; i <= Math.sqrt(num); i++) { //Math.sqrt() = 숫자의 제곱근을 반환, 루트이용!
				if(num % i == 0) { //
					amiprime = false;
					break;
				}
			}
			if(amiprime) {
				yesprime++;
			}
		}
		System.out.println(yesprime);
	}
 
}