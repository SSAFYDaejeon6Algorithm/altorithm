package day0429;

import java.util.*;

public class b18405_경쟁적전염 {
    static int N, K;
    static int[][] map;
    static int S, X, Y;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static  ArrayList<Point> arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        map = new int[N + 1][N + 1];
        arr = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] != 0) arr.add(new Point(i, j, map[i][j]));
            }
        }
        Collections.sort(arr);
        S = sc.nextInt();
        X = sc.nextInt();
        Y = sc.nextInt();

        bfs();
        System.out.println(map[X][Y]);
    }

    static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.addAll(arr);

        int time = 0;
        while (time < S) {
            if (map[X][Y] != 0) return;
            int size = q.size();
            for (int s = 0; s < size; s++) {
                Point now = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = now.i + dr[d];
                    int nc = now.j + dc[d];

                    if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && map[nr][nc] == 0) {
                        map[nr][nc] = now.v;
                        q.add(new Point(nr, nc, map[nr][nc]));
                    }
                }
            }
            time++;
        }

    }

    static class Point implements Comparable<Point>{
        int i, j, v;

        public Point(int i, int j, int v) {
            this.i = i;
            this.j = j;
            this.v = v;
        }

        @Override
        public int compareTo(Point o) {
            return this.v - o.v;
        }
    }
}
