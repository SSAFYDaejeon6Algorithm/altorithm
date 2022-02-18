package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2810_cupHolder {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		String s = br.readLine();
		
		StringBuilder sb = new StringBuilder(" ");
		
		for(int n=0; n<N; n++) {				// 컵홀더 자리까지 추가한 배열 생성
			sb.append(s.charAt(n));
			if(s.charAt(n)=='L' && s.charAt(n+1)=='L') {
				sb.append(s.charAt(++n));
			}
			sb.append(" ");
		}
		
		int cnt = 0;
		char[] seat = sb.toString().toCharArray();
		
		for(int i=0, len=seat.length; i<len; i++) {	// 컵 채워보기
			if(seat[i]=='S' || seat[i]=='L' ){
				
				if(seat[i-1]==' ') seat[i-1] = 'X';
				else if(seat[i+1]==' ') seat[i+1] = 'X';
				else continue;
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
