class Solution {
    
    int aeiou(char c) {
      switch(c){
      case 'A': return 1;
      case 'E': return 2;
      case 'I': return 3;
      case 'O': return 4;
      case 'U': return 5;
      }
      return 0;
	  }
    
    public int solution(String word) {
        int[] words = new int[5];
        for(int i=0; i<5; i++) {
          if(i<word.length()) words[i] = aeiou(word.charAt(i));
          else words[i] = 0;
        }

        int answer = 0;
        for(int i=1; i<=5; i++) { // 단어의 길이
          for(int j=0; j<i; j++) { // 앞자리부터, 각 자리수 이전에 오는 단어수 세기
            if(words[j]==0) continue;
            answer += (words[j]-1) * Math.pow(5, i-j-1);
          }
          if(i<=word.length()) answer++;
        }
        return answer;
    }
}
