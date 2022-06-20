
public class P이진변환반복하기 {
	
	public int[] solution(String s) {
        int[] answer = new int[2]; // [실행횟수, 0 개수]
        
        while(!s.equals("1")) {
        	for(int i=0; i<s.length(); i++) if(s.charAt(i) == '0') answer[1]++;
        	answer[0]++;
        	String binary = toBinary(s.replaceAll("0", "").length());
        	s = binary;
        }
        
        return answer;
	}
	
  //숫자를 인자로 넣고 호출하면 2진수로 변환한 string을 반환한다
	static String toBinary(int num) {
		StringBuffer binary = new StringBuffer();
		
		while(num > 0) {
			if(num % 2 == 0) binary.append(0);
			else binary.append(1);
			num /= 2;
		}
		
		return binary.reverse().toString();
	}
}
