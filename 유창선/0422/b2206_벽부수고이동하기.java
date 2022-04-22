package day0422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b2206_벽부수고이동하기 {
    static int[][] map;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0 ,0};
    static int[] dc = {0, 0, -1, 1};
    static int N, M;
    static int res = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        } // end input

        bfs();

        System.out.println(res);
    }

    static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 0));
        visited[0][0][0] = true;
        visited[0][0][1] = true;
        int move = 1;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int s = 0; s < size; s++) {
                Point now = q.poll();

                if (now.i == N -1 && now.j == M - 1) {
                    res = move;
                    return;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = now.i + dr[d];
                    int nc = now.j + dc[d];

                    if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc][now.flag]) {
                        if (map[nr][nc] == 1 && now.flag == 1) continue;
                        if (map[nr][nc] == 1 && now.flag == 0){
                            visited[nr][nc][1] = true;
                            q.add(new Point(nr, nc, 1));
                        } else {
                            visited[nr][nc][now.flag] = true;
                            q.add(new Point(nr, nc, now.flag));
                        }
                    }
                }
            }
            move++;
        }
    }

    static class Point {
        int i, j, flag = 1;

        Point (int i, int j, int flag) {
            this.i = i;
            this.j = j;
            this.flag = flag;
        }
    }
}
