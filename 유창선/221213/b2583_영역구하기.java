package day1213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b2583_영역구하기 {
    static int M, N, K;
    static int[][] map;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int y = y1; y < y2; y++) {
                for (int x = x1; x < x2; x++) {
                    map[y][x] = 1;
                }
            }
        } // end input

        for (int i = 0 ; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) bfs(i, j);
            }
        }
        Collections.sort(list);
        System.out.println(list.size());
        for (int s : list) System.out.print(s + " ");
    }


    public static void bfs(int sr, int sc) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sr, sc));
        int cnt = 1;
        map[sr][sc] = 1;
        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nr >= M || nc < 0 || nc >= N || map[nr][nc] == 1) continue;
                map[nr][nc] = 1;
                q.add(new Point(nr, nc));
                cnt++;
            }
        }
        list.add(cnt);
    }

    public static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }



}
