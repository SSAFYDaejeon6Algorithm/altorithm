package day0223;

import java.util.Scanner;

public class b9663_NQeeun {
    static int N, res;
    static boolean[] colUsed;
    static int[] qPos;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        colUsed = new boolean[N];
        qPos = new int[N];
        placeQ(0);
        System.out.println(res);

    }
    static void placeQ(int cnt) {
        for (int i = cnt - 2; i >=0; i--) { // 대각선 체크
            if (Math.abs(qPos[cnt - 1] - qPos[i]) == (cnt - 1) - i) { // Queen의 row 값의 차이와 col의 차이가 같다면 대각선
                return;
            }
        }
        if (cnt == N) {
            res++;
            return;
        }

        for (int j = 0; j < N; j++) { // j는 열을 의미
            if (!colUsed[j]) {
                qPos[cnt] = j; // index 값이 row , qPos[index] 값이 column
                colUsed[j] = true;
                placeQ(cnt + 1);
                colUsed[j] = false;
            }
        }
    }

}
