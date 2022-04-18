package day0415;

import java.util.Scanner;

public class b17070_파이프옮기기1 {
    static int N;
    static int[][]map;
    static int[][][] count;
    static int[] dr = {0, 1, 1}; // 우, 우하, 하
    static int[] dc = {1, 1, 0};
    static String[] directions = {
            "01", "012", "12"
    };
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = sc.nextInt();
            }
        } // end input


        count = new int[N + 1][N + 1][3];
        count[1][2][0] = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 2; j <= N; j++) { // 2부터 시작해야함.
                int size = getSize(i, j);
                if (map[i][j] == 1) continue; // 벽이면 패스
                if (size < 1) continue; // 파이프 온 것 없다면 패스.

                for (int k = 0; k < 3; k++) {
                    if (count[i][j][k] == 0) continue;
                    String s = directions[k]; // 현재 파이프 방향
                    for (int d = 0; d < s.length(); d++) {
                        int dir = s.charAt(d) - '0'; // 현재 파이프 방향에 따라 놓을 수 있는 파이프 방향
                        int nr = i + dr[dir];
                        int nc = j + dc[dir];

                        if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && map[nr][nc] != 1) {
                            if (dir == 1) {
                                if (map[nr - 1][nc] == 1 || map[nr][nc - 1] == 1) continue; // 파이프 대각선 경우 3개 다 체크
                            }
                            count[nr][nc][dir] += count[i][j][k];
                        }
                    }
                }
            }
        }

        System.out.println(getSize(N, N));

    }

    static int getSize(int i, int j) {
        int size = 0;
        for (int k = 0; k < 3; k++) {
            size += count[i][j][k]; // 해당 좌표 파이프 수 계산
        }
        return size;
    }
}
