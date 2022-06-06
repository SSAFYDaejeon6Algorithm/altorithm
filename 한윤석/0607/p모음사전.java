package programmers;

public class P모음사전 {

	public static void main(String[] args) {
		String word = "EIO";
		System.out.println(solution(word));
	}

	static int solution(String word) {
		int answer = 0;
		char alpha [] = {'A','E','I','O','U'};
		int cost [] = {781, 156, 31, 6, 1}; //i+1번째 자리의 값을 바꾸기 위해 필요한 횟수
		
		for(int i=0; i<word.length(); i++) {
			char cur = word.charAt(i);
			for(int j=0; j<5; j++) {
				if(cur == alpha[j]) answer += j * cost[i]; //앞에서부터 j번 만큼의 횟수를 반복해야 alpha[j]로 변경 가능
			}
		}
		
        return answer+word.length();
	}
}
