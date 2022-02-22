package day0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2116_주사위쌓기 {
    static int n;
    static int[][] dices; // 05 , 13, 24 인덱스끼리 위아래
    static int ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dices = new int[n][6];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dices[i][j] = Integer.parseInt(st.nextToken());
            }
        } // end input

        for (int i = 0; i < 6; i++) {
            recur(0, i + 1, 0);
        }
        System.out.println(ans);

    }

    static void recur(int cnt, int prevTop, int sum) {
        int bottom = -1;
        int top = -1;
        int max = Integer.MIN_VALUE;
        if (cnt >= n) {
            ans = Math.max(ans, sum);
            return;
        }


        for (int i = 0; i < 6; i++) {
            if (dices[cnt][i] == prevTop) { // 전의 윗면을 가지고 아랫면 찾기
                bottom = i;
                break;
            }
        }

        if (bottom == 0) top = 5;
        else if (bottom == 1) top = 3;
        else if (bottom == 2) top = 4;
        else if (bottom == 3) top = 1;
        else if (bottom == 4) top = 2;
        else if (bottom == 5) top = 0;

        for (int i = 0; i < 6; i++) {
            if (i == bottom || i == top) continue;
            max = Math.max(max, dices[cnt][i]); // 옆면 중 가장 큰 것 찾기
        }

        recur(cnt + 1, dices[cnt][top], sum += max); // 현재 윗면의 주사위 값을 전달 (index 값 아님!!)

    }

}
