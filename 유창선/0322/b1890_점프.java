package day0322;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b1890_점프 {
    static int[][] map;
    static boolean[][] visited;
    static long[][] cntMap;
    static int N;
    static int endR, endC;

    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        cntMap = new long[N][N];
        endR = endC = N - 1;
        cntMap[endR][endC] = 1; // 마지막 지점 1 (마지막 지점 -> 마지막 지점은 1개)
        visited[endR][endC] = true;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(endR, endC);
        System.out.println(cntMap[0][0]);
    }

   static void bfs(int r, int c) {

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(r, c - 1));
        visited[r][c-1] = true;
        q.offer(new Point(r - 1, c));
        visited[r - 1][c] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int curR = cur.r;
            int curC = cur.c;
            int jumpVal = map[curR][curC];
            int jumpR = curR + jumpVal; // 점프한 후의 행
            int jumpC = curC + jumpVal; // 점프한 후의 열
            long cnt = 0;

            if (jumpR < N) cnt += cntMap[jumpR][curC]; // 아래로 점프하면 도착점까지 갈 수 있는 경우의 수
            if (jumpC < N) cnt += cntMap[curR][jumpC]; // 오른쪽 점프하면 도착점까지 갈 수 있는 경우의 수

            cntMap[curR][curC] = cnt; // 현재 위치에서 목적지까지 갈 수 있는 경우의 수

            // 다음 넣어주기 (현재지점에서 왼쪽)
            if (curC - 1 >= 0 && !visited[curR][curC - 1]) {
                q.offer(new Point(curR, curC - 1));
                visited[curR][curC - 1] = true;
            }
            // 다음 넣어주기 (현재지점에서 윗쪽)
            if (curR - 1 >= 0 && !visited[curR - 1][curC]) {
                q.offer(new Point(curR - 1, curC));
                visited[curR - 1][curC] = true;
            }
        }
   }

   static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
   }
}
