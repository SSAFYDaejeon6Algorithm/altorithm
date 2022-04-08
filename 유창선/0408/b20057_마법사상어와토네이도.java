package day0408;

import java.util.Scanner;

public class b20057_마법사상어와토네이도 {
    static int N;
    static Tornado tornado;
    static int[] dr = {0, 1, 0, -1}; // 좌 하 우 상
    static int[] dc = {-1, 0, 1, 0};
    static int[][] map;
    static int outSand;
    static int step, dir;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        tornado = new Tornado(N/2, N/2); // 시작점 가운데
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        } // end input
        step = 1; // 움직이는 횟수
        dir = 0; // 왼쪽 방향 시작

        move();
        System.out.println(outSand);
    }

    public static void move() {
        while (true) {
            for (int i = 0; i < 2; i++) { // 1칸 이동 2번, 2칸 이동 2번 ...
                for (int s = 0; s < step; s++) {
                    tornado.r += dr[dir];
                    tornado.c += dc[dir];
                    if (tornado.c == -1) return; // 마지막까지 다 날렸다면 끝!!
                    splitSand(tornado.r, tornado.c);
                    map[tornado.r][tornado.c] = 0;
                }
                dir = (dir + 1) % 4; // 방향 전환
            }
            step++;
        }
    }

    public static void splitSand(int r, int c) {
        int[] percentage = new int[11];
        percentage[1] = (int) Math.floor(map[r][c] * 0.01);
        percentage[2] = (int) Math.floor(map[r][c] * 0.02);
        percentage[5] = (int) Math.floor(map[r][c] * 0.05);
        percentage[7] = (int) Math.floor(map[r][c] * 0.07);
        percentage[10] = (int) Math.floor(map[r][c] * 0.1);


        int firstSevenDir = (dir + 1 + 4) % 4; // 양 사이드에 있는 7%로 가기 위한 방향 설정이다.
        int secondSevenDir = (dir - 1 + 4) % 4;

        calSideArea(percentage, r, c, firstSevenDir); // 첫번째 7퍼센트 구역
        calSideArea(percentage, r, c, secondSevenDir); // 두번째 7퍼센트 구역
        calFrontArea(percentage, r, c, dir); // 원래 진행 방향
        calAlpha(percentage, r, c, dir);

    }

    public static void calSideArea(int[] p, int r, int c, int sevenDir) { // 퍼센트 값 배열, 7%로 갈 수 있었던 방향
        int nr = r + dr[sevenDir];
        int nc = c + dc[sevenDir];

        if (nr >= 0 && nr < N && nc >= 0 && nc < N) { // 모래밭 안이라면 기존 좌표와 값 더하기
            map[nr][nc] += p[7];
        } else {                // 밖이라면 밖에 더하기
            outSand += p[7];
        }

        int d = (sevenDir + 2) % 4; // 3방 탐색 (이 방향 빼고 하면 됨)
        for (int i=0; i < 4; i++) {
            if (i == d) continue;
            int nnr = nr + dr[i];
            int nnc = nc + dc[i];

            if (nnr >= 0 && nnr < N && nnc >= 0 && nnc < N) { //
                if (i == dir) { // 전체 dir 진행 방향과 같다면 10 %
                    map[nnr][nnc] += p[10];
                } else if (i == sevenDir) { // 7퍼센트 칸으로 온 방향과 같다면 2 %
                    map[nnr][nnc] += p[2];
                } else { // 1 %
                    map[nnr][nnc] += p[1];
                }
            } else {
                if (i == dir) { // 전체 dir 진행 방향과 같다면 10 %
                    outSand += p[10];
                } else if (i == sevenDir) { // 7퍼센트 칸으로 온 방향과 같다면 2 %
                    outSand += p[2];
                } else { // 1 %
                    outSand += p[1];
                }
            }
        }
    }

    public static void calFrontArea(int[] p, int r, int c, int dir) {
        int nr = r + dr[dir] * 2;
        int nc = c + dc[dir] * 2;

        if (nr >= 0 && nr < N && nc >= 0 && nc < N) { // 모래밭 안이라면 기존 좌표와 값 더하기
            map[nr][nc] += p[5];
        } else {                // 밖이라면 밖에 더하기
            outSand += p[5];
        }
    }

    public static void calAlpha(int[] p, int r, int c, int dir) {
        int nr = r + dr[dir];
        int nc = c + dc[dir];
        int sand = p[1] * 2 + p[2] * 2 + p[5] + p[7] * 2 + p[10] * 2;
        int alpha = map[r][c] - sand;
        if (nr >= 0 && nr < N && nc >= 0 && nc < N) { // 모래밭 안이라면 기존 좌표와 값 더하기
            map[nr][nc] += alpha;
        } else {                // 밖이라면 밖에 더하기
            outSand += alpha;
        }
    }

    public static class Tornado {
        int r, c;
        Tornado(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
