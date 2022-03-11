package day0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class b1941_소문난칠공주 {
    static int[][] map;
    static boolean[][] visited;
    static int res;
    static int[] di = {0, 1, -1, 0};
    static int[] dj = {1, 0, 0, -1};
    static final int Y = 0;
    static int startI = -1;
    static int startJ = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[5][5];
        visited = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                if (line.charAt(j) == 'Y') map[i][j] = 0;
                else map[i][j] = 1;
            }
        } //end input

        backtracking(0, -1,0, 0);

        System.out.println(res);
    }

    static void backtracking(int beforeI, int beforeJ, int yCnt, int cnt) {
        if (yCnt >= 4) return; // 임도연파가 더 많다면 back
        if (cnt >= 7) {
            int isConnected = bfs(startI, startJ); // 연결되어 있나 탐색
            if (isConnected == 7) res++; // 연결되어 있으면 늘려주기
            return;
        }

        for (int i = beforeI; i < 5; i++) {
            int j = (i == beforeI) ? beforeJ + 1 : 0; // 전에 픽했던 학생 그 다음 순서부터 (조합이기 때문)
            for (; j < 5; j++) {
                if (cnt == 0) {
                    startI = i;  // 학생이 연결되어 있나 확인하기 위한 출발점 i
                    startJ = j;  // 학생이 연결되어 있나 확인하기 위한 출발점 j
                }
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    if (map[i][j] == Y) backtracking(i, j, yCnt + 1, cnt + 1);
                    else backtracking(i, j, yCnt, cnt + 1);
                    visited[i][j] = false;
                }
            }
        }
    }

    static int bfs(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(r, c));
        int cnt = 1;
        // visited 배열 복사
        boolean[][] tmp = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tmp[i][j] = visited[i][j];
            }
        }
        tmp[r][c] = false; // 현재 학생 체크
        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextI = cur.i + di[i];
                int nextJ = cur.j + dj[i];
                if (nextI >= 0 && nextI < 5 && nextJ >= 0 && nextJ < 5 && tmp[nextI][nextJ]) {
                    tmp[nextI][nextJ] = false; // 다음 학생 체크
                    q.offer(new Point(nextI, nextJ));
                    cnt++; // 연결된 수 늘려주기
                }
            }
        }
        return cnt;
    }

    static class Point {
        int i, j;
        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

    }
}
