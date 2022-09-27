package day0920;

import java.util.Scanner;

public class b16987_계란으로계란치기 {
    static int N;
    static int res = 0;
    static int[][] eggs;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        eggs = new int[N][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                eggs[i][j] = sc.nextInt();
            }
        } // end input

        hit(0, 0);
        System.out.println(res);

    }

    public static void hit(int now, int cnt) {
        if (now == N) {
            res = Math.max(cnt, res);
            return;
        }
        // 단, 손에 든 계란이 깨졌거나 깨지지 않은 다른 계란이 없으면 치지 않고 넘어간다
        if (eggs[now][0] <= 0 || cnt == N - 1) {
            hit(now + 1, cnt);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (now == i || eggs[i][0] <= 0) continue;
            int tmpCnt = cnt;
            eggs[now][0] -= eggs[i][1];
            eggs[i][0] -= eggs[now][1];
            if (eggs[now][0] <= 0) tmpCnt++;
            if (eggs[i][0] <= 0) tmpCnt++;
            hit (now + 1, tmpCnt); // 다음 옆에 계란 들어서 깨자
            eggs[now][0] += eggs[i][1];
            eggs[i][0] += eggs[now][1];
        }
    }
}
