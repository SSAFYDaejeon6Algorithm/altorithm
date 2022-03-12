package day0315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b21610_마법사상어와비바라기 {
    static int N, M, D, S;
    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1}; // 1~8 인덱스 사용하기 위해 처음에 0 추가
    static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[][] map;
    static boolean[][] cloudMap;
    static Queue<Cloud> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        cloudMap = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // end input
        q = new LinkedList<>(); // 구름 위치 빨리 찾기 위해 담는 큐

        initCloud();

        for (int m = 1; m <= M; m++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            moveCloudAndWater(D, S);
            waterCopyBug();
            newCloudMap();
        }
        System.out.println(sumWater());
    }

    static void initCloud() {
        for (int i= N -1; i <= N; i++) {
            for (int j = 1; j <= 2; j++) {
                cloudMap[i][j] = true;
                q.offer(new Cloud(i, j));
            }
        }
    }

    static void moveCloudAndWater(int d, int s) {
        boolean[][] copyCloudMap = new boolean[N + 1][N + 1];
        int index = 0;
        int size = q.size();
        while (index++ < size) {
            Cloud cloud = q.poll();
            int i = cloud.r;
            int j = cloud.c;
            int nr = i + dr[d] * s;
            int nc = j + dc[d] * s;
            /*------------row 이동--------------*/
            if (nr <= 0) {
                while (nr <= 0) nr += N;
            }
            else if (nr >= N + 1) {
                nr %= N;
            }
            if(nr == 0) nr = N; // 나머지가 0이란걸 N번째를 의미

            /*------------col 이동--------------*/
            if (nc <= 0) {
                while (nc <= 0) nc += N;
            }
            else if (nc >= N + 1) {
                nc %= N;
            }
            if (nc == 0) nc = N;

            copyCloudMap[nr][nc] = true; // 구름 위치 체크
            map[nr][nc]++; // 물 증가
            q.offer(new Cloud(nr, nc)); // 움직인 구름 있는 위치 큐에 넣어주기
        }
        cloudMap = copyCloudMap;
    }

    static void waterCopyBug() {
        int index = 0;
        int size = q.size();
        while (index++ < size) {
            Cloud cloud = q.poll();
            int i = cloud.r;
            int j = cloud.c;
            for (int d = 2; d <= 8; d+=2) {
                int nr = i + dr[d];
                int nc = j + dc[d];
                if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && map[nr][nc] != 0) {
                    map[i][j]++; // 대각선에 물 들어 있으면 물 복사
                }
            }
            q.offer(cloud); // 물복사버그 하느라 구름 위치 꺼내서 썼으니 다시 구름 있는 위치 큐에 넣어주기
        }
    }

    static void newCloudMap() {
        int index = 0;
        int size = q.size();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!cloudMap[i][j] && map[i][j] >= 2) {
                    map[i][j] -= 2;
                    cloudMap[i][j] = true;
                    q.offer(new Cloud(i, j)); // 새로운 구름 위치 넣어주기
                } else if (cloudMap[i][j]) {
                    cloudMap[i][j] = false;
                }
            }
        }
        while (index++ < size) {
            q.poll(); // 전에 있던 구름들 전부 삭제!
        }
    }

    static int sumWater() {
        int sum = 0;
        for (int i= 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sum += map[i][j];
            }
        }
        return sum;
    }

    static class Cloud {
        int r, c;
        Cloud(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
