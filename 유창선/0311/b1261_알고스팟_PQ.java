package day0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b1261_알고스팟_PQ {

    static int N, M;
    static int pickCnt;
    static int[][] map;
    static int[][] minWeights;
    static boolean[][] visited;
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    static class Point implements Comparable<Point> {
        int i, j, weight;
        Point(int i, int j, int weight) {
            this.i = i;
            this.j = j;
            this.weight = weight;
        }

        @Override
        public int compareTo(Point o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        pickCnt =  N *M;
        map = new int[N][M];
        visited = new boolean[N][M];
        minWeights = new int[N][M];

        for (int i = 0; i < N; i++){
            Arrays.fill(minWeights[i], Integer.MAX_VALUE); // 비용 무한대로 초기화
        }

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        } // end input


        dijstra();
        System.out.println(minWeights[N-1][M-1]);
    }

    static void dijstra() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        minWeights[0][0] = 0; // 출발점 weight 0으로 만듦
        pq.offer(new Point(0, 0, 0)); // pq에 넣어줌

        while (!pq.isEmpty()) {
            Point minPos = pq.poll();
            if (visited[minPos.i][minPos.j]) continue; // 이미 최소 비용 고려했다면 continue;

            visited[minPos.i][minPos.j] = true;

            for (int d = 0; d < 4; d++) {
                int nextR = minPos.i + di[d];
                int nextC = minPos.j + dj[d];

                if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < M
                        && minPos.weight + map[nextR][nextC] < minWeights[nextR][nextC]) { // 거리가 짧으면 업데이트
                    pq.offer(new Point(nextR, nextC, minPos.weight + map[nextR][nextC])); // Pq에 넣어줌
                    minWeights[nextR][nextC] = minPos.weight + map[nextR][nextC];
                }
            }
        }
    }

}
