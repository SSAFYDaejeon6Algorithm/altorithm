package day0325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b16927_배열돌리기2 {
    static int N, M, R;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0, N-1, 0, M -1);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }


    static void recur(int minR, int maxR, int minC, int maxC) {
        if (minR >= maxR || minC >= maxC) return;

        int tmp = (maxR - minR + 1)*2 + (maxC - minC + 1)*2 -4;
        for (int i = 0; i < R % tmp; i++) {
            for (int c = minC; c < maxC; c++) { // 윗변
                swap(minR, c, minR, c + 1);
            }

            for (int r = minR; r < maxR; r++) {
                swap(r, maxC, r + 1, maxC);
            }

            for (int c = maxC; c > minC; c--) {
                swap(maxR, c, maxR, c - 1);
            }

            for (int r = maxR; r > minR + 1; r--) {
                swap(r, minC, r - 1, minC);
            }
        }

        recur(minR + 1, maxR - 1, minC + 1, maxC - 1);

    }


    static void swap(int r1, int c1, int r2, int c2) {
        int tmp = map[r1][c1];
        map[r1][c1] = map[r2][c2];
        map[r2][c2] = tmp;
    }
}
