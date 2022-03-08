package day0308;

import java.util.Scanner;

public class b1245_농장관리 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0, 1, 1, -1, -1};
    static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
    static int cnt;
    static boolean isTop;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j= 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                isTop = true;
                if (!visited[i][j]) {
                    dfs(i, j);
                    if (isTop) cnt++; // 주변에 아무것도 시작점보다 높은 것이 없다면 최고점
                }
            }
        }

        System.out.println(cnt);
    }

    static void dfs(int r, int c) {
        visited[r][c]= true;
        for (int d = 0 ; d < 8; d++) {
            int nextR = r + dr[d];
            int nextC = c + dc[d];

            if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < M
                    && !visited[nextR][nextC] && map[nextR][nextC] == map[r][c]) {
                dfs(nextR, nextC);
            } else if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < M && map[nextR][nextC] > map[r][c]) {
                isTop = false; // 주변에 높은거 있으면 정상 아님
            }
        }
    }
}
