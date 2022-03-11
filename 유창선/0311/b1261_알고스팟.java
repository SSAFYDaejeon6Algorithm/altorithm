package day0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b1261_알고스팟 {

    static int N, M;
    static int pickCnt;
    static int[][] map;
    static int[][] minWeights;
    static boolean[][] visited;
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

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
        minWeights[0][0] = 0; // 출발점 weight 0으로 만들어둬서 선택되게끔
        Point minPos = new Point(0, 0); // 출발점 weight

        while (pickCnt > 0) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j] && minWeights[i][j] < min) { // 가장 작은 비용 선택
                        min = minWeights[i][j];
                        minPos = new Point(i, j);
                    }
                }
            }

            visited[minPos.i][minPos.j] = true;
            pickCnt--;

            for (int d = 0; d < 4; d++) {
                int nextR = minPos.i + di[d];
                int nextC = minPos.j + dj[d];

                if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < M
                        && min + map[nextR][nextC] < minWeights[nextR][nextC]) { // 거리가 짧으면 업데이트
                    minWeights[nextR][nextC] = min + map[nextR][nextC];
                }
            }
        }
    }

    static class Point {
        int i, j;
        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
