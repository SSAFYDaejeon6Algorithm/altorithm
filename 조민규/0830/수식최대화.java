package programmers;

import java.util.ArrayList;
import java.util.List;

class Solution {

    static char[][] ps = {
            {'*', '+', '-'}, {'*', '-', '+'},
            {'+', '-', '*'}, {'+', '*', '-'},
            {'-', '+', '*'}, {'-', '*', '+'}
    };

    static List<Long> aa = new ArrayList<>();
    static List<Character> bb = new ArrayList<>();
    static long ans = 0;

    public long calc(char exp, long s1, long s2){
        if(exp == '*'){
            return s1 * s2;
        } else if(exp == '+'){
            return s1 + s2;
        } else if(exp == '-'){
            return s1 - s2;
        }
        return 0L;
    }

    public void test(int t){
        // 초기 설정
        List<Long> a = new ArrayList<>(); // 숫자만 담긴 배열
        List<Character> b = new ArrayList<>(); // 연산자만 담긴 배열
        for (Long l : aa) {
            a.add(l);
        }
        for(Character c : bb){
            b.add(c);
        }

        for(int i = 0 ; i < 3 ; i++){ // 3개 연산자에 대해 반복 진행
            char operator = ps[t][i]; // 현재 연산자
            for(int j = 0 ; j < b.size() ; j++){
                if(b.get(j) == operator){
                    a.set(j, calc(b.get(j), a.get(j), a.get(j+1)));
                    a.remove(j+1);
                    b.remove(j);
                    --j;
                }
            }
        }

        if(Math.abs(ans) < Math.abs(a.get(0))){
            ans = a.get(0);
        }
    }

    public long solution(String expression) {
        // 숫자와 수식 분리하기
        int start = 0;
        for(int i = 0 ; i < expression.length() ; i++){
            if(expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*') {
                long newA = Long.parseLong(expression.substring(start, i));
                aa.add(newA);
                bb.add(expression.charAt(i));
                start = i + 1;
            } else if(i == expression.length()-1){
                long newA = Long.parseLong(expression.substring(start, i+1));
                aa.add(newA);
            }
        }

        for(int t = 0 ; t < ps.length ; t++){
            test(t);
        }
        return Math.abs(ans);
    }
}

public class 수식최대화 {
    public static void main(String[] args) {
        String exp = "100-200*300-500+20";
        Solution s = new Solution();
        System.out.println(s.solution(exp));
    }
}
