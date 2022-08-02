package day0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b25307_시루의백화점구경 {
    public static int[][] map;
    public static boolean[][] visited;
    public static int N, M, K, sr, sc, dest, min = -1;
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
//                    visited[i][j] = true;
                }
                else if (map[i][j] == 2) {
                    dest++;
                } else if (map[i][j] == 3) {
                    fillMannequin(i, j);
                }else if (map[i][j] == 4) {
                    sr = i;
                    sc = j;
                }
            }
        } // end input
        if (dest != 0) bfs();
        System.out.println(min);
    }


    public static void fillMannequin(int i, int j) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i, j));
        visited[i][j] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                int mdist = Math.abs(i - nr) + Math.abs(j - nc); // 마네킹과의 거리
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && mdist <= K) {
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc));
                }
            }
        }
    }


    public static void bfs() {
        Queue<Point> q = new LinkedList<>();
        visited[sr][sc] = true;
        q.add(new Point(sr, sc));
        int dist = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                Point p = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];

                    if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] != 1) {
                        visited[nr][nc] = true;
                        q.add(new Point(nr, nc));
                        if (map[nr][nc] == 2) { // 의자 도착
                            min = dist + 1;
                            return;
                        }
                    }
                }
            }
            dist++;
        }
    }


    public static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
