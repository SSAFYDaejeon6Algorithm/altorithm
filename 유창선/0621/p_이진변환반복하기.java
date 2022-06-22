package day0621;

import java.util.*;

class Solution {
    public int[] solution(String s) {
        int zCnt = 0, cnt = 0, len = 0;

        while (true) {
            if (s.equals("1")) break;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') zCnt++;
                else len++;
            }
            s = Integer.toBinaryString(len);
            cnt++;
            len = 0;
        }

        int[] answer = {cnt, zCnt};
        return answer;
    }
}
