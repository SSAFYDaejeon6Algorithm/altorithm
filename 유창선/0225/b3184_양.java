package day0225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b3184_양 {
    static int R, C;
    static int[] di = {-1, 0, 1, 0}; // 0: up, 1: right, 2: down, 3: left
    static int[] dj = {0, 1, 0, -1};
    static boolean[][] visited;
    static char[][] map;
    static int wolfCnt, sheepCnt; // 전체 늑대, 양
    static int tmpWolf, tmpSheep; // 현재 구역 늑대, 양
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '#') visited[i][j] = true; // 울타리 못간다고 표시
                else if (map[i][j] == 'v') wolfCnt++;
                else if (map[i][j] == 'o') sheepCnt++;
            }
        } // end input

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j]) {
                    tmpSheep = 0; // 현재 구역 양
                    tmpWolf = 0; // 현재 구역 늑대
                    dfs(i, j);
                    if (tmpWolf >= tmpSheep) sheepCnt -= tmpSheep; // 현재 구역 늑대가 같거나 많다면
                    else wolfCnt -= tmpWolf; // 양이 많다면
                }
            }
        }

        System.out.println(sheepCnt + " " + wolfCnt);
    } // end main

    static void dfs(int r, int c) {

        visited[r][c] = true;
        if (map[r][c] == 'v') tmpWolf++;
        else if (map[r][c] == 'o') tmpSheep++;

        for (int i = 0; i < 4; i++) {
            int nextR = r + di[i];
            int nextC = c + dj[i];
            if (nextR >= 0 && nextR < R && nextC >=0 && nextC < C && !visited[nextR][nextC]) {
                dfs(nextR, nextC);
            }
        }

    }
}
