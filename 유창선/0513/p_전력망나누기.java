package day0513;

import java.util.*;

public class p_전력망나누기 {
    static int[][] map;
    static int N;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) {

    }

    public int solution(int n, int[][] wires) {
        N = n;
        map = new int[N + 1][N + 1]; // 송전탑 연결 그리기 위한 맵
        for (int i = 0; i < N - 1; i++) {
            int from = wires[i][0];
            int to = wires[i][1];
            map[from][to] = 1;
            map[to][from] = 1;
        }

        for (int i = 0; i < N - 1; i++) {
            int from = wires[i][0];
            int to = wires[i][1];
            map[from][to] = 0; // 연결 해제
            map[to][from] = 0; // 연결 해제

            int a = bfs(from); // 연결된 송전탑 구하기
            int b = bfs(to); // 연결된 송전탑 구하기

            min = Math.min(min, Math.abs(a - b)); // 둘의 차이 구하기

            map[from][to] = 1; // 다시 송전탑 복구
            map[to][from] = 1; // 다시 송전탑 복구

        }

        return min;

    }

    static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        q.offer(start);
        int cnt = 1;
        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next = 1; next <= N; next++) {
                if (next == now) continue;
                if (map[now][next] == 1 && !visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                    cnt++;
                }
            }
        }
        return cnt++;
    }
}
