package day0218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class b2810_컵홀더 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Character> stack = new Stack<>();
        String line = br.readLine();
        for (int i = 0; i < N - 1; i++) {
            if (line.charAt(i) == 'S') { // S 나오면 *S 넣기
                stack.push('*');
                stack.push('S');
            } else if (line.charAt(i) == 'L') { // L 나오면 *LL 넣기
                stack.push('*');
                stack.push('L');
                stack.push('L');
                i++;
            }
        }
        stack.push('*'); // 마지막 컵홀더 자리 * 넣기

        int starCnt = 0;
        while (!stack.isEmpty()) {
            if (stack.pop() == '*') { // *이면 컵둘 수 있음 증가
                starCnt++;
            } else { // 문자라면 컵둘 수 있는 수 감소
                starCnt--;
            }
        }

        System.out.println(starCnt >=0 ? N : N + starCnt); // 0 이상이면 모두 둘 수 있음
                                                            // 음수면 그 만큼 전체 인원에서 못 둠
    }
}
