package day0830;

import java.util.ArrayList;
import java.util.List;

public class kakao_2020_수식최대화 {

    public static char[] op = new char[3]; // 0 -> '+', 1 -> '-', 2 -> '*'
    public static List<Long> numList = new ArrayList<>(); // 숫자를 넣는 덱
    public static List<Character> opList = new ArrayList<>(); // 연산자를 넣는 덱
    public static long max = Long.MIN_VALUE;
    public static long solution(String expression) {
        long answer = 0;
        perm(0, 0, expression);
        answer = max;
        return answer;
    }

    public static void perm(int cnt, int picked, String exp) {
        if (cnt >= 3) { // 3개 다 뽑았다면
            if (numList.isEmpty()) parsing(exp);
            max = Math.max(max, findAnswer());
            return;
        }

        for (int i = 0; i < 3; i++) { // '+', '-', '*' 뽑기
            if ((picked & 1 << i) > 0) continue; // 해당 연산자 뽑았으면 continue

            if (i == 0) op[cnt] = '+';
            else if (i == 1) op[cnt] = '-';
            else op[cnt] = '*';
            perm(cnt + 1, picked | 1 << i, exp);
        }
    }

    public static void parsing(String exp) {
        int start = 0, end = 0;
        int len = exp.length();

        while (end < len) {
            char tmpOp = exp.charAt(end);
            while (tmpOp != '-' && tmpOp != '+' && tmpOp != '*' ) {
                end++;
                if (end >= len) {
                    tmpOp = ' '; // 마지막은 연산자 안옴
                    break;
                }
                tmpOp = exp.charAt(end);
            }
            numList.add(Long.parseLong(exp.substring(start, end))); // 숫자 넣기
            if (tmpOp != ' ') opList.add(tmpOp); // 연산자 넣기
            end += 1;
            start = end;
        }
    }

    public static long findAnswer() {
        List<Long> nList = new ArrayList<>();
        nList.addAll(numList); // 숫자 리스트 복사
        List<Character> oList = new ArrayList<>();
        oList.addAll(opList); // 연산자 리스트 복사
        for (int i = 0; i < 3; i++) {
            char operator = op[i];
            int size = oList.size();
            for (int j = 0; j < size; j++) {
                if (oList.get(j) == operator) {
                    long res = calc(nList.get(j), nList.get(j + 1), oList.get(j));
                    nList.remove(j);
                    nList.remove(j);
                    nList.add(j, res);
                    oList.remove(j);
                    j--;
                    size = oList.size();
                }
            }
        }
        return Math.abs(nList.get(0));
    }

    public static long calc(long num1, long num2, char operator) {
        if (operator == '+') return num1 + num2;
        else if (operator == '-') return num1 - num2;
        else return num1 * num2;
    }

//    public static void main(String[] args) {
//        System.out.println(solution("100-200*300-500+20"));
//    }
}
