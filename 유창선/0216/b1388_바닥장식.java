package day0216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1388_바닥장식 {

    static int cnt = 0;
    static int N;
    static int M;
    static int[] di = {0, 1}; // 0 : right, 1 : down
    static int[] dj = {1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        for (int n = 0; n < N; n++) {
            String line = br.readLine();
            for (int m = 0; m < M; m++) {
                map[n][m] = line.charAt(m);
            }
        } // end input

        for (int i = 0; i < N; i++) {
            for (int j =0; j < M; j++) {
                if (map[i][j] != '0') // 방문했던 곳 아니면 dfs 시작
                    dfs(map, i, j, map[i][j]);
            }
        }
        System.out.println(cnt);
    }

    public static void dfs(char[][] map, int r, int c, int ch) {

        if (r >= N || c >= M || map[r][c] != ch) { // 배열 초과했거나 전의 모양과 다르다면 그 전까지 유효하니 일단 수 늘리고 돌아가기
            cnt++;
            return;
        }

        // 전의 타일 모양과 동일하다면
        if (map[r][c] == '-') { // 오른쪽으로 이동
            map[r][c] = '0'; // 왔던 길 없애기
            dfs(map, r, c + 1, '-');
        }
        else if (map[r][c] == '|') { // 아래로 이동
            map[r][c] = '0'; // 왔던 길 없애기
            dfs(map, r + 1, c, '|');
        }

    }
}
