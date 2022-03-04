package day0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b2638_치즈 {
    static int N, M;
    static int[][] map; // 치즈가 있는 맵
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};
    static int[][] airCnt; // 치즈가 공기 닿는 면적 갯수 체크
    static boolean[][] visited; // 방문했는지
    static int cheeseCnt;
    static int hours;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        airCnt = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) cheeseCnt++; // 처음 치즈 갯수 세주기 (치즈가 없어질 때까지 while 돌기 위해)
            }
        } // end input

        while (cheeseCnt > 0) { // 치즈가 남아있을 동안
            visited = new boolean[N][M];
            airCnt = new int[N][M];
            bfs(new Point(0, 0));

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1 && airCnt[i][j] >= 2) { // 2면 이상 닿았으면
                        map[i][j] = 0;
                        cheeseCnt--; // 남은 치즈 갯수 줄여주기
                    }
                }
            }
            hours++; // 1시간 증가
        }

        System.out.println(hours);
    }

    static void bfs(Point p) {
        Queue<Point> q = new LinkedList<>();
        q.offer(p);
        visited[p.i][p.j] = true;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            int r = cur.i;
            int c = cur.j;

            for (int d = 0; d < 4; d++) { // 4방 탐색
                int nextR = r + di[d];
                int nextC = c + dj[d];

                if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < M  // 치즈가 아닌 부분
                        && !visited[nextR][nextC] && map[nextR][nextC] == 0) {
                    visited[nextR][nextC] = true;
                    q.offer(new Point(nextR, nextC));
                } else if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < M  // 치즈인 부분
                        && !visited[nextR][nextC] && map[nextR][nextC] == 1) {
                    airCnt[nextR][nextC]++; // 해당 치즈에 공기가 닿는 수 더해주기
                }
            }

        }
    }

    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
