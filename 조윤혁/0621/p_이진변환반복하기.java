class Solution {
    static int zeroLen = 0; // 제거한 0의 수
    static int cnt = 0; // 이진변환 횟수
    
    public int[] solution(String s) {
       do {
			zeroLen += s.length() - s.replaceAll("0", "").length(); // 제거할 0의 수 누적
			s = s.replaceAll("0", ""); // 1. 모든 0제거
			s = Integer.toBinaryString(s.length()); // 2. 0을 제거한 후 문자열의 길이를 이진변환
			cnt++;
		} while (s.length() != 1); // 0 제거 후 길이가 1이 될때까지 반복
        
        return new int[] {cnt, zeroLen};
    }
}