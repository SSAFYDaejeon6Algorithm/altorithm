package day0330;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b1520_내리막길 {
    static int N, M;
    static int[][] map;
    static int[][] way;
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        way = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        way[N-1][M-1] = 1; // 도착 지점 1
        dfs(0, 0);
        System.out.println(way[0][0]);

    }

    static void dfs(int r, int c) {

        if (r == N - 1 && c == M - 1) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + di[i];
            int nc = c + dj[i];


            if (nr >= 0 && nr < N && nc >= 0 && nc < M
                    && map[r][c] > map[nr][nc]) {
                if (way[nr][nc] >= 1) {
                    way[r][c] += way[nr][nc];
                }else if (way[nr][nc] < 1 && !visited[nr][nc]){
                    visited[nr][nc] = true;
                    dfs(nr, nc); // dfs 돌고 오면 다음 칸 업데이트되어 있음
                    way[r][c] += way[nr][nc]; // 그 값 현재 칸에 더해줌.
                }
            }
        }
    }

}
