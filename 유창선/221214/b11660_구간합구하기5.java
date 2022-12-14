package year2022.day1214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b11660_구간합구하기5 {
    static int N, M;
    static int[][] map;
    static int[][] sumMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        sumMap = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                sumMap[i][j] = sumMap[i][j - 1] + sumMap[i - 1][j] + map[i][j] - sumMap[i - 1][j - 1];
            }
        } // end input

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int i1 = Integer.parseInt(st.nextToken()); // 행
            int j1 = Integer.parseInt(st.nextToken()); // 열
            int i2 = Integer.parseInt(st.nextToken());
            int j2 = Integer.parseInt(st.nextToken());
            System.out.println(sumMap[i2][j2] - sumMap[i2][j1 - 1] - sumMap[i1 - 1][j2] + sumMap[i1 - 1][j1 - 1]);
        }
    }
}
