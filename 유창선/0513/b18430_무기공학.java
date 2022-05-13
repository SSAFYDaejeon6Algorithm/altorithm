package day0513;

import java.util.*;
import java.io.*;

public class b18430_무기공학 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // end input

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j, 0);
            }
        }
        System.out.println(max);
    }

    static void dfs(int r, int c, int sum) {
        if (c == M) { // 컬럼이 맨 초과했다면 범위 조정
            r += 1;
            c = 0;
            if (r == N) {
                max = Math.max(max, sum);
                return;
            }
        }

        if (visited[r][c]) { // 다른 부메랑이 현재 칸 차지하고 있으면 다음 칸
            dfs(r, c + 1, sum);
        } else {
            // 현재 칸 빈칸이면 4가지 경우 고려해서 놓음
            for (int d = 0; d < 4; d++) {
                int cnt = 0;

                for (int k = 0; k < 2; k++) {
                    int dir = (d + k) % 4;
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];

                    if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
                        cnt++;
                    }
                }
                if (cnt != 2) continue;

                // 부메랑 놓을 수 있다면 부메랑 놓인 자리 체크 및 값 더한 후
                visited[r][c] = true;
                int tmp = sum + map[r][c] * 2;
                for (int k = 0; k < 2; k++) {
                    int dir = (d + k) % 4;
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];
                    visited[nr][nc] = true;
                    tmp += map[nr][nc];
                }
                dfs(r, c + 1, tmp); // 현재 칸 부메랑 놓고 다음 칸 넘어가기
                for (int k = 0; k < 2; k++) {
                    int dir = (d + k) % 4;
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];
                    visited[nr][nc] = false;
                }
                visited[r][c] = false;
            }

            // 현재칸 비어있지만 놓지 않고(or 놓지 못하고) 다음 칸으로
            dfs(r, c + 1, sum);
        }
    }
}
