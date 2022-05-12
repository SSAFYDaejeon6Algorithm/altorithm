import java.util.*;
import java.io.*;
class Solution {
    static int[][] costBoard;
    static boolean[][][] visited;
    static int answer;
    static int N;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int minCost;

    public int solution(int[][] board) {
        N = board.length;
        costBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                costBoard[i][j] = Integer.MAX_VALUE;
            }
        }
        visited = new boolean[N][N][4];
        minCost = Integer.MAX_VALUE;

        answer = bfs(board);
        return answer;
    }


    static int bfs(int[][] board) {
        Queue<Point> q = new LinkedList<>();
        costBoard[0][0] = 0;
        q.offer(new Point(0, 0, -1, 0));
        for (int i = 0; i < 4; i++) {
            visited[0][0][i] = true;
        }

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int prevDir = cur.prevDir;
            int sum = cur.sum;
            if (r == N -1 && c == N - 1) continue; // 마지막 칸이라면 다시 나아갈 필요 X

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < N && nc >= 0 && nc < N && board[nr][nc] == 0) { // board 유효 범위인가
                    int cost = 100;
                    int dir = d;
                    if (r != 0 || c != 0) { // 0, 0 이 아니면 방향과 비용 계산해줘야함
                        dir = Math.abs(prevDir - d);
                        cost = dir % 2 == 0 ? 100 : 600;
                    }

                    if (sum + cost <= costBoard[nr][nc]){ // 비용이 같거나 작은경우에만
                        visited[nr][nc][d] = true;
                        costBoard[nr][nc] = sum + cost;
                        q.offer(new Point(nr, nc, d, costBoard[nr][nc]));
                    } else if (!visited[nr][nc][d]) { // 처음온 방향이라면 비용이 크더라도 일단 큐에 넣음 (후에 더 작아질 수 있기 때문)
                        visited[nr][nc][d] = true;
                        q.offer(new Point(nr, nc, d, sum + cost));
                    }
                }
            }
        }
        return costBoard[N - 1][N - 1];
    }


    static class Point {
        int r, c, prevDir, sum;
        public Point(int r, int c, int prevDir, int sum) {
            this.r = r;
            this.c = c;
            this.prevDir = prevDir;
            this.sum = sum;
        }
    }
}
