import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S25215_typing {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] ch = br.readLine().toCharArray();
		
		boolean button = false, clicked = false; // button : true(대), false(소) / clicked : 이전 입력에서 버튼을 눌렀는지 여부
		int cnt = 1, result=0;
		for(int i=0; i<ch.length; i++) {
			result++;
			//대소문자 변화가 있는 경우
			if((ch[i]-'a'<0 && !button) || (ch[i]-'a'>=0 && button)) { 
				if(i==0 || cnt!=1 || (cnt==1 && clicked==false)) { // 버튼이 눌린경우 (별인지 마름모인지 모름)
					result++;
					clicked = true;
				}else {
					clicked = false; // 이전에 눌린 버튼이 별 버튼일 경우
				}
				cnt=1;
				button = !button;
			}// 이전과 대소문자가 동일한 경우
			else { 
				clicked = false;
				cnt++;
			}
		}
		System.out.println(result);
	}
}
