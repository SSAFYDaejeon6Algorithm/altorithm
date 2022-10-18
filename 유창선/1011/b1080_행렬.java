package day1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1080_행렬 {
    static int N, M;
    static int[][] a;
    static int[][] b;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        a = new int[N][M];
        b = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                a[i][j] = s.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                b[i][j] = s.charAt(j) - '0';
            }
        } // end input

        for (int i = 0; i <= N - 3; i++) {
            for (int j = 0; j <= M - 3; j++) {
                if (a[i][j] != b[i][j]) {
                    cnt++;
                    for (int r = i; r < i + 3; r++) {
                        for (int c = j; c < j + 3; c++) {
                            if (a[r][c] == 0) a[r][c] = 1;
                            else a[r][c] = 0;
                        }
                    }
                }
            }
        }

        if (checkAB()) System.out.println(cnt);
        else System.out.println(-1);


    }

    static boolean checkAB() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }
}
