package day0216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b21608_상어초등학교 {
    static int N;
    static int[] di = {0, 1, 0, -1}; // 0: right, 1 : down, 2: left, 3: up
    static int[] dj = {1, 0, -1, 0};
    static int[][] map;
    static int[][] like;
    static int sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        like = new int[N * N + 1][5];

        for (int i = 0; i <  N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int person = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= 4; j++) {
                like[person][j] = Integer.parseInt(st.nextToken());
            }

            int maxCnt = Integer.MIN_VALUE;
            int maxEmpty = Integer.MAX_VALUE;
            int maxI = -1;
            int maxJ = -1;
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if (map[r][c] != 0) continue;

                    int cnt = 0; // 좋아하는 친구 몇 명 있는지
                    int emptyCnt = 0;
                    for (int dir = 0; dir < 4; dir++) { // 4방 탐색
                        int nextI = r + di[dir];
                        int nextJ = c + dj[dir];
                        if (nextI < 1 || nextI > N || nextJ < 1 || nextJ > N) continue;

                        for (int f = 1; f <= 4; f++) {
                            if (map[nextI][nextJ] == like[person][f]) { // 좋아하는 친구 목록에 있다면 cnt 늘려주기
                                cnt++;
                                break;
                            } else if (map[nextI][nextJ] == 0) emptyCnt++;
                        }
                    }
                    if (cnt > maxCnt || (cnt == maxCnt && emptyCnt > maxEmpty)) { // 가장 친구 많은 곳 표시 or 친구 같으면 주변 빈칸 많은 곳으로
                        maxCnt = cnt; // 주변 친구 수
                        maxEmpty = emptyCnt; // 주변 빈칸
                        maxI = r;
                        maxJ = c;
                    } // 1, 2조건 만족

                } // 3조건은 1, 1부터 시작하므로 자동으로 만족
            }

            map[maxI][maxJ] = person;

        } // end making map

        // 친구 수에 따라 점수 매기기
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                int cnt = 0;  // 인접 친구 몇명 있나
                int person = map[r][c];
                for (int dir = 0; dir < 4; dir++) { // 4방 탐색
                    int nextI = r + di[dir];
                    int nextJ = c + dj[dir];
                    if (nextI < 1 || nextI > N || nextJ < 1 || nextJ > N) continue;

                    for (int f = 1; f <= 4; f++) {
                        if (map[nextI][nextJ] == like[person][f]) cnt++;
                    }
                }
                switch(cnt) {
                    case 0:
                        break;
                    case 1:
                        sum += 1;
                        break;
                    case 2:
                        sum += 10;
                        break;
                    case 3:
                        sum += 100;
                        break;
                    case 4:
                        sum += 1000;
                        break;
                }
            }
        }
        System.out.println(sum);
    }
}
