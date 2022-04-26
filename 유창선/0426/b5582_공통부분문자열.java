package day0426;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b5582_공통부분문자열 {
    static String a, b;
    static int[][] map;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine();
        b = br.readLine();
        map = new int[a.length() + 1][b.length() + 1]; // 글자 0번째 부터 넣기 위함
        max = 0;
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) { // 글자가 같다면
                    map[i][j] = map[i - 1][j - 1] + 1; // 그 전 글자까지 연속된 글자 수에 1더함
                    max = Math.max(max, map[i][j]); // 최댓값 갱신
                } else {
                    map[i][j] = 0;
                }
            }
        }

        System.out.println(max);

    }
}
