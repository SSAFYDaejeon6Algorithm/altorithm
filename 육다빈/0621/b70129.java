import java.util.*;

class Solution {
    public int[] solution(String s) {
        int total=s.length(), cnt_zero = 0, cnt_binary=0;
        
        // 초기 0 제거
        char[] ch = s.toCharArray();
        for(char c : ch) if(c=='0') cnt_zero++;
        
        // 1의개수 -> 이진법 변환
        int cnt_one = total - cnt_zero;
        while(cnt_one > 1){
            int origin = cnt_one;
            int now = cnt_one;
            cnt_one = 0;
            for(int i=18; i>=0; i--){
                if(origin<Math.pow(2, i)) {
                    continue; //유효하지 않은 앞자리 skip
                }else if(now>=Math.pow(2, i)) {
                    now -= Math.pow(2, i);
                    cnt_one++;
                }else{
                    cnt_zero++;
                }
            }
            cnt_binary++;
        }
        
        int[] answer = new int[2];
        answer[0] = cnt_binary+1;
        answer[1] = cnt_zero;
        return answer;
    }
}
